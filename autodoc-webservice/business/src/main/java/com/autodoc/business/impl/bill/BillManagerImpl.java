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
import com.autodoc.model.enums.PaymentType;
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

    public SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm");
    }

    public Class getEntityClass() {
        return Bill.class;
    }

    public Class getDtoClass() {
        return BillDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao");
        return dao;
    }


    @Override
    public BillDTO entityToDto(Object entity) {
        LOGGER.info("convert to dto");
        Bill bill = (Bill) entity;
        BillDTO dto = new BillDTO();
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

        if (tasks != null) {
            for (Task task : tasks) {
                taskList.add(task.getId());
            }
            dto.setTasks(taskList);
        }

        List<Integer> pieceList = new ArrayList<>();
        List<Piece> pieces = bill.getPieces();
        if (pieces != null) {
            for (Piece piece : pieces) {
                pieceList.add(piece.getId());
            }
        }
        dto.setPieces(pieceList);
        LOGGER.info("bill dto: " + dto);
        return dto;
    }

    public void updateBillStatusIfMissingPiece(Bill bill) {
        for (Piece piece : bill.getPieces()) {
            if (piece.getName().startsWith("OOS")) bill.setStatus(Status.PENDING_PIECES);
        }
    }

    @Override
    public Bill transferUpdate(Object obj) {
        //resetException();
        BillDTO dto = (BillDTO) obj;
        if (dto.getId() == 0) throw new InvalidDtoException("no id provided");
        Bill bill = (Bill) dao.getById(dto.getId());
        if(dto.getDiscount()!=0)bill.setDiscount(dto.getDiscount());
        if(dto.getStatus()!=null)bill.setStatus(Status.valueOf(dto.getStatus()));
        if(dto.getTotal()!=0)bill.setTotal(dto.getTotal());
        bill.setVat(BillDTO.VAT);
        if(dto.getComments()!=null)bill.setComments(dto.getComments());
        if(dto.getPaymentType()!=null)bill.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));

        transferDateReparation(dto, bill);
        transferCar(dto, bill);

        transferTasks(dto, bill);
        transferPieces(dto, bill);

        transferClient(dto, bill);
        transferEmployee(dto, bill);

        updateBillStatusIfMissingPiece(bill);
        LOGGER.info("bill transferred: " + bill);
        return bill;
    }

    @Override
    public Bill dtoToEntity(Object entity) {
        //resetException();
        BillDTO dto = (BillDTO) entity;
        Bill bill = new Bill();
        bill.setId(dto.getId());
        bill.setDiscount(dto.getDiscount());
        bill.setStatus(Status.valueOf(dto.getStatus()));
        bill.setTotal(dto.getTotal());
        bill.setVat(BillDTO.VAT);
        bill.setComments(dto.getComments());
        bill.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));

        transferDateReparation(dto, bill);
        transferCar(dto, bill);

        transferTasks(dto, bill);
        transferPieces(dto, bill);

        transferClient(dto, bill);
        transferEmployee(dto, bill);
        LOGGER.info("bill transferred: " + bill);
        return bill;
    }

    public void transferEmployee(BillDTO dto, Bill bill) {
        Employee employee = (Employee) employeeDao.getById(dto.getEmployeeId());
        if (employee == null) throw new InvalidDtoException("invalid employee reference: " + dto.getEmployeeId());
        bill.setEmployee(employee);
    }

    public void transferClient(BillDTO dto, Bill bill) {
        Client client = (Client) clientDao.getById(dto.getClientId());
        if (client == null) throw new InvalidDtoException("invalid client reference: " + dto.getClientId());

        bill.setClient(client);
    }

    public void transferCar(BillDTO dto, Bill bill) {
        Car car = carDao.getCarByRegistration(dto.getRegistration());
        if (car == null) throw new InvalidDtoException("car cannot be null");
        bill.setCar(car);
    }

    public void transferDateReparation(BillDTO dto, Bill bill) {
        if (dto.getDateReparation() != null) {
            try {
                bill.setDateReparation(getDateFormat().parse(dto.getDateReparation()));
            } catch (ParseException e) {
                throw new InvalidDtoException("invalid date format: " + dto.getDateReparation());
            }
        }
    }

    public void transferPieces(BillDTO dto, Bill bill) {
        if (dto.getPieces() != null) {
            List<Piece> pieceList = new ArrayList<>();
            for (Integer i : dto.getPieces()) {
                Piece piece = (Piece) pieceDao.getById(i);
                if (piece == null) throw new InvalidDtoException("invalid piece");
                pieceList.add(piece);
            }
            updateStockAndAddPieces(pieceList, bill.getPieces());
            bill.setPieces(pieceList);
        }
    }

    public void transferTasks(BillDTO dto, Bill bill) {
        if (dto.getTasks() != null) {
            List<Task> taskList = new ArrayList<>();
            for (Integer i : dto.getTasks()) {
                Task task = (Task) taskDao.getById(i);
                if (task == null) throw new InvalidDtoException("invalid task");
                taskList.add(task);
            }
            bill.setTasks(taskList);
        }
    }


    public void updateStockAndAddPieces(List<Piece> billPieces, List<Piece> billDbPieces) {

        LOGGER.info("updating stock");
        LOGGER.info(billPieces);
        LOGGER.info(billDbPieces);

        if (billDbPieces != null && !billDbPieces.isEmpty()) {

            // raises db quantity if removing a new item from the bill
            for (Piece p : billDbPieces) {
                if (!billPieces.contains(p)) {
                    LOGGER.info("adding an item");
                    pieceManager.updateQuantity(p, "+");
                }
                dao.update(p);
            }
        }


        if (billPieces != null && !billPieces.isEmpty()) {

            // lowers db quantity if adding a new item from the bill
            for (Piece p : billPieces) {
                if (billDbPieces != null && !billDbPieces.isEmpty()) {
                    if (!billDbPieces.contains(p)) {
                        LOGGER.info("adding an item");
                        pieceManager.updateQuantity(p, "-");
                    }
                }
                dao.update(p);
            }
        }

    }
}
