package com.autodoc.business.impl;


import com.autodoc.business.contract.*;
import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.dtos.bill.BillForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;

import javax.inject.Named;
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

    public BillManagerImpl(BillService service, CarManager carManager, TaskManager taskManager, ClientManager clientManager, EmployeeManager employeeManager) {
        super(service);
        this.service = service;
        this.carManager = carManager;
        this.taskManager = taskManager;
        this.clientManager = clientManager;
        this.employeeManager = employeeManager;
    }

    public Bill dtoToEntity(String token, Object obj) throws Exception {

        BillDTO dto = (BillDTO) obj;
        LOGGER.info("dto found: " + dto);
        Bill bill = new Bill();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        bill.setId(id);
        bill.setDate(dto.getDate());
        Car car = carManager.getByRegistration(token, dto.getRegistration());
        if (car == null) throw new Exception("car cannot be null");
        LOGGER.info("car found: "+car);
        bill.setCar(car);
        bill.setDiscount(dto.getDiscount());
        bill.setStatus(dto.getStatus());
        bill.setTasks(getTasks(token, dto.getTasks()));
        Client client = (Client) clientManager.getById(token, dto.getClientId());
        if(client ==null)throw new Exception("invalid client reference: "+dto.getClientId());
        LOGGER.info("client found: "+client);
        bill.setClient(client);
        bill.setTotal(dto.getTotal());
        bill.setVat(dto.getVat());
        Employee employee = (Employee) employeeManager.getById(token, dto.getEmployeeId());
        if(employee ==null)throw new Exception("invalid employee reference: "+dto.getEmployeeId());
        LOGGER.info("employee found: "+employee);
        bill.setEmployee(employee);
        LOGGER.info("bill transferred: " + bill);

        return bill;
    }


    private List<Task> getTasks(String token, List<Integer> tasks) throws Exception {
        List<Task> taskList = new ArrayList<>();
        for (Integer i: tasks){
            Task task = (Task) taskManager.getById(token, i);
            if(task == null)throw new Exception("invalid task reference: "+i);
            taskList.add(task);
        }
        return taskList;
    }

    public BillDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        BillForm form = (BillForm) obj;
        LOGGER.info("dto: " + form);
        BillDTO dto = new BillDTO();
        dto.setId(form.getId());
        dto.setClientId(form.getClient().getId());
        dto.setDate(form.getDate());
        dto.setDiscount(form.getDiscount());
        dto.setEmployeeId(form.getEmployee().getId());
        dto.setRegistration(form.getCar().getRegistration());
        dto.setStatus(form.getStatus());
        dto.setTasks(form.getTasks());
        dto.setTotal(form.getTotal());
        dto.setVat(form.getVat());
        LOGGER.info("bill transferred: " + dto);
        return dto;
    }


    @Override
    public List<Bill> getByRegistration(String token, SearchDto searchDto) {
        return service.getByCriteria(token, searchDto);
    }
}
