package com.autodoc.business.impl;


import com.autodoc.business.contract.*;
import com.autodoc.contract.BillService;
import com.autodoc.contract.EnumService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.dtos.bill.BillForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named
public class BillManagerImpl extends GlobalManagerImpl<Bill, BillDTO> implements BillManager {

    private static final Logger LOGGER = Logger.getLogger(BillManagerImpl.class);
    public Bill bill;
    private BillService service;
    private CarManager carManager;
    private TaskManager taskManager;
    private ClientManager clientManager;
    private EmployeeManager employeeManager;
    private PieceManager pieceManager;
    private EnumService enumService;
    private SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public BillManagerImpl(BillService service, CarManager carManager, TaskManager taskManager, ClientManager clientManager, EmployeeManager employeeManager, PieceManager pieceManager, EnumService enumService) {
        super(service);
        this.service = service;
        this.carManager = carManager;
        this.taskManager = taskManager;
        this.pieceManager = pieceManager;
        this.clientManager = clientManager;
        this.employeeManager = employeeManager;
        this.enumService = enumService;
    }

    public Bill dtoToEntity(String token, Object obj) throws Exception {

        BillDTO dto = (BillDTO) obj;
        LOGGER.info("dto to entity: " + dto);
        Bill bill = new Bill();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        bill.setId(id);
        LOGGER.info("old date: " + dto.getDateReparation());
        bill.setDateReparation(mdyFormat.parse(dto.getDateReparation()));
        LOGGER.info("new date: " + bill.getDateReparation());
        Car car = carManager.getByRegistration(token, dto.getRegistration());
        if (car == null) throw new Exception("car cannot be null");
        LOGGER.info("car found: " + car);
        bill.setCar(car);
        bill.setDiscount(dto.getDiscount());
        bill.setStatus(dto.getStatus());
        bill.setTasks(getTasks(token, dto.getTasks()));
        bill.setPieces(gerPieces(token, dto.getPieces()));
        Client client = (Client) clientManager.getById(token, dto.getClientId());
        if (client == null) throw new Exception("invalid client reference: " + dto.getClientId());
        LOGGER.info("client found: " + client);
        bill.setClient(client);
        bill.setTotal(dto.getTotal());
        bill.setVat(dto.getVat());
        Employee employee = (Employee) employeeManager.getById(token, dto.getEmployeeId());
        if (employee == null) throw new Exception("invalid employee reference: " + dto.getEmployeeId());
        LOGGER.info("employee found: " + employee);
        bill.setEmployee(employee);
        LOGGER.info("bill transferred: " + bill);
        bill.setComments(dto.getComments());
        return bill;
    }


    private List<Task> getTasks(String token, List<Integer> tasks) throws Exception {
        List<Task> taskList = new ArrayList<>();
        for (Integer i : tasks) {
            Task task = (Task) taskManager.getById(token, i);
            if (task == null) throw new Exception("invalid task reference: " + i);
            taskList.add(task);
        }
        return taskList;
    }


    private List<Piece> gerPieces(String token, List<Integer> pieces) throws Exception {
        List<Piece> pieceList = new ArrayList<>();
        for (Integer i : pieces) {
            Piece piece = (Piece) pieceManager.getById(token, i);
            if (piece == null) throw new Exception("invalid task reference: " + i);
            pieceList.add(piece);
        }
        return pieceList;
    }

    public BillDTO formToDto(Object obj, String token) throws Exception {
        LOGGER.info("form to dto: " + obj);
        BillForm form = (BillForm) obj;
        LOGGER.info("dto: " + form);
        BillDTO dto = new BillDTO();
        dto.setId(form.getId());
        dto.setClientId(form.getClientId());


        String mdy = mdyFormat.format(form.getDateReparation());
        dto.setDateReparation(mdy);
        LOGGER.info("date passed: " + dto.getDateReparation());
        dto.setDiscount(form.getDiscount());
        Employee employee = employeeManager.getByLogin(token, form.getEmployeeLogin());
        dto.setEmployeeId(employee.getId());
        dto.setRegistration(form.getCarRegistration());
        dto.setStatus(form.getStatus());
        List<Integer> taskIdList = new ArrayList<>();
        for (Integer taskId : form.getTasks().getList()) {
            if (taskId != null)
                taskIdList.add(taskId);
        }
        List<Integer> pieceIdList = new ArrayList<>();
        if(form.getPieces()!=null) {
            for (Integer pieceId : form.getPieces().getList()) {
                if (pieceId != null)
                    pieceIdList.add(pieceId);
            }
        }
        dto.setPieces(pieceIdList);
        dto.setComments(form.getComments());
        dto.setTasks(taskIdList);
        dto.setTotal(calculateTotal(token, taskIdList));
        dto.setVat(form.getVat());
        LOGGER.info("bill transferred: " + dto);
        return dto;
    }

    private double calculateTotal(String token, List<Integer> taskIds) throws Exception {
        double total = 0;
        for (int taskId : taskIds) {
            Task task = (Task) taskManager.getById(token, taskId);
            if (task != null) total += (task.getEstimatedTime() * pricePerHour);
        }
        return total;
    }


    @Override
    public List<Bill> getByRegistration(String token, SearchDto searchDto) {
        return service.getByCriteria(token, searchDto);
    }

    @Override
    public List<String> getStatus(String token) {
        return enumService.getAll(token, "status");
    }
}
