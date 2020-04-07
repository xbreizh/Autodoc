package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
@Builder
public class BillManagerImpl extends AbstractGenericManager implements BillManager {
    private static final Logger LOGGER = Logger.getLogger(BillManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private BillDao dao;
    private CarDao carDao;
    private ClientDao clientDao;
    private EmployeeDao employeeDao;
    private PieceDao pieceDao;
    private PieceManager pieceManager;
    private TaskDao taskDao;
    private TaskManager taskManager;

    protected SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm");
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao");
        return dao;
    }


    @Override
    public BillDTO entityToDto(Object entity) {
        LOGGER.info("convert to dto");
        BillDTO dto = new BillDTO();
        Bill bill = (Bill) entity;
        dto.setId(bill.getId());
        dto.setRegistration((bill).getCar().getRegistration());
        dto.setClientId((bill).getClient().getId());
        dto.setEmployeeId((bill).getEmployee().getId());
        dto.setDiscount((bill).getDiscount());
        dto.setTotal((bill).getTotal());
        dto.setStatus((bill).getStatus().toString());
        Date dateRepa = (bill).getDateReparation();
        dto.setDateReparation(getDateFormat().format(dateRepa));
        dto.setComments((bill).getComments());
        List<Integer> taskList = new ArrayList<>();
        List<Task> tasks = (bill).getTasks();


        for (Task task : tasks) {
            taskList.add(task.getId());
        }
        dto.setTasks(taskList);

        List<Integer> pieceList = new ArrayList<>();
        List<Piece> pieces = bill.getPieces();
        for (Piece piece : pieces) {
            pieceList.add(piece.getId());
        }

        dto.setPieces(pieceList);
        LOGGER.info("bill dto: " + dto);
        return dto;
    }

    private void updateBillStatusIfMissingPiece(Bill bill) {
        for (Piece piece : bill.getPieces()) {
            if (piece.getName().startsWith("OOS")) bill.setStatus(Status.PENDING_PIECES);
        }
    }

    @Override
    public Bill transferUpdate(Object obj) throws InvalidDtoException {
        resetException();
        BillDTO dto = (BillDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id provided");
        Bill bill = (Bill) dao.getById(dto.getId());
        if (bill == null) throw new InvalidDtoException("no bill found with that reference: " + dto.getId());
        if (dto.getDateReparation() != null) {
            try {
                bill.setDateReparation(getDateFormat().parse(dto.getDateReparation()));
            } catch (ParseException e) {
                throw new InvalidDtoException("invalid date format: " + dto.getDateReparation());
            }
        }
        LOGGER.info("updating car");
        if (dto.getRegistration() != null && !dto.getRegistration().isEmpty()) {
            Car car = carDao.getCarByRegistration(dto.getRegistration());
            if (car == null) throw new InvalidDtoException("car cannot be null");
            LOGGER.info("car found: " + car);
            bill.setCar(car);
        }
        LOGGER.info("updating discount");
        if (dto.getDiscount() != 0) bill.setDiscount(dto.getDiscount());
        LOGGER.info("updating tasks");
        if (dto.getTasks() != null) {
            List<Task> taskList = new ArrayList<>();
            for (Integer i : dto.getTasks()) {
                Task task = (Task) taskDao.getById(i);
                if (task == null) throw new InvalidDtoException("invalid task");
                taskList.add(task);
            }
            bill.setTasks(taskList);
        }
        LOGGER.info("tasks set: " + bill.getTasks());
        LOGGER.info("updating pieces: " + bill.getPieces());
        List<Piece> oldPieceList = bill.getPieces();
        if (dto.getPieces() != null) {
            List<Piece> newPieceList = new ArrayList<>();
            for (Integer i : dto.getPieces()) {
                Piece piece = (Piece) pieceDao.getById(i);
                if (piece == null) throw new InvalidDtoException("invalid piece");
                newPieceList.add(piece);
            }
            bill.setPieces(newPieceList);
            LOGGER.info("old list: " + oldPieceList);
            LOGGER.info("new list: " + newPieceList);
            pieceManager.updateStockAndAddPieces(newPieceList, oldPieceList);
            updateBillStatusIfMissingPiece(bill);
        }
        LOGGER.info("pieces list: " + bill.getPieces());
        LOGGER.info("updating client");
        if (dto.getClientId() != 0) {
            Client client = (Client) clientDao.getById(dto.getClientId());
            if (client == null) throw new InvalidDtoException("invalid client reference: " + dto.getClientId());
            LOGGER.info("client found: " + client);
            bill.setClient(client);
        }
        LOGGER.info("updating total");
        if (dto.getTotal() != 0) bill.setTotal(dto.getTotal());
        LOGGER.info("updating vat");
        if (BillDTO.VAT != 0) bill.setVat(BillDTO.VAT);
        LOGGER.info("updating employee");
        if (dto.getEmployeeId() != 0) {
            Employee employee = (Employee) employeeDao.getById(dto.getEmployeeId());
            if (employee == null) throw new InvalidDtoException("invalid employee reference: " + dto.getEmployeeId());
            bill.setEmployee(employee);
            LOGGER.info("employee found: " + employee);
        }
        LOGGER.info("updating comments");
        if (dto.getComments() != null && !dto.getComments().isEmpty()) {
            bill.setComments(dto.getComments());
        }
        LOGGER.info("updating status");

        if (dto.getStatus() != null && !dto.getStatus().isEmpty() && bill.getStatus() == null) {
            bill.setStatus(Status.valueOf(dto.getStatus()));
        }
        LOGGER.info("bill transferred: " + bill);
        return bill;
    }

    @Override
    public Bill dtoToEntity(Object entity) throws InvalidDtoException {
        resetException();
        BillDTO dto = (BillDTO) entity;
        LOGGER.info("dto found: " + dto);
        Bill bill = new Bill();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        bill.setId(id);
        // if (dto.getDateReparation()==null && bill.getDateReparation()==null)throw new InvalidDtoException("no valid date provided: "+dto.getDateReparation());
        LOGGER.info("dto date: " + dto.getDateReparation());
        try {
            bill.setDateReparation(getDateFormat().parse(dto.getDateReparation()));
        } catch (ParseException e) {
            throw new InvalidDtoException("invalid date format: " + dto.getDateReparation());
        }
        LOGGER.info("entity date: " + bill.getDateReparation());
        Car car = carDao.getCarByRegistration(dto.getRegistration());
        if (car == null) throw new InvalidDtoException("car cannot be null");
        LOGGER.info("car found: " + car);
        bill.setCar(car);
        bill.setDiscount(dto.getDiscount());
        bill.setStatus(Status.valueOf(dto.getStatus()));
        if (dto.getTasks() != null) {
            List<Task> taskList = new ArrayList<>();
            for (Integer i : dto.getTasks()) {
                Task task = (Task) taskDao.getById(i);
                if (task == null) throw new InvalidDtoException("invalid task");
                taskList.add(task);
            }
            bill.setTasks(taskList);
        }
        LOGGER.info("tasks set: " + bill.getTasks());
        if (dto.getPieces() != null) {
            List<Piece> pieceList = new ArrayList<>();
            for (Integer i : dto.getPieces()) {
                Piece piece = (Piece) pieceDao.getById(i);
                if (piece == null) throw new InvalidDtoException("invalid piece");
                pieceList.add(piece);
            }
            pieceManager.updateStockAndAddPieces(pieceList, bill.getPieces());
            bill.setPieces(pieceList);
        }
        Client client = (Client) clientDao.getById(dto.getClientId());
        if (client == null) throw new InvalidDtoException("invalid client reference: " + dto.getClientId());
        LOGGER.info("client found: " + client);
        bill.setClient(client);
        bill.setTotal(dto.getTotal());
        bill.setVat(BillDTO.VAT);
        Employee employee = (Employee) employeeDao.getById(dto.getEmployeeId());
        if (employee == null) throw new InvalidDtoException("invalid employee reference: " + dto.getEmployeeId());
        bill.setComments(dto.getComments());
        LOGGER.info("employee found: " + employee);
        bill.setEmployee(employee);
        LOGGER.info("bill transferred: " + bill);
        checkIfDuplicate(dto);
        return bill;
    }

}
