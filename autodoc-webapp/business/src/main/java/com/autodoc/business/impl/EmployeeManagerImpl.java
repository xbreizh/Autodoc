package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.contract.EnumService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class EmployeeManagerImpl extends GlobalManagerImpl<Employee, EmployeeDTO> implements EmployeeManager {

    private static Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);


    EmployeeService service;
    EnumService enumService;


    public EmployeeManagerImpl(EmployeeService service, EnumService enumService) {
        super(service);
        this.service = service;
        this.enumService = enumService;
    }

    @Override
    public Employee getByLogin(String token, String login) {
        System.out.println("serviceee: " + service);
        return dtoToEntity(token, service.getByName(token, login));
    }

    @Override
    public List<String> getRoles(String token) {
        return enumService.getAll(token, "roles");
    }

    public Employee dtoToEntity(String token, Object obj) {

        EmployeeDTO dto = (EmployeeDTO) obj;
        LOGGER.info("dto: " + dto);
        Employee employee = new Employee();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        employee.setId(id);
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles().toString());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setPhoneNumber2(dto.getPhoneNumber2());
        employee.setStartDate(dto.getStartDate());
        employee.setLastConnection(dto.getLastConnection());
        LOGGER.info("entity transferred: " + employee);

        return employee;
    }

    public EmployeeDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        EmployeeForm dto = (EmployeeForm) obj;
        LOGGER.info("dto: " + dto);
        LOGGER.info(dto.getFirstName());
        EmployeeDTO employee = new EmployeeDTO();
        if (dto.getId() != 0) employee.setId(dto.getId());
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setPhoneNumber2(dto.getPhoneNumber2());
        if (dto.getPassword() != null) employee.setPassword(dto.getPassword());
        LOGGER.info("entity transferred: " + employee);
        return employee;
    }

    public List<Employee> convertList(List<Object> list) {
        LOGGER.info("converting list");
        List<Employee> newList = new ArrayList<>();
        for (Object obj : list) {
            EmployeeDTO dto = (EmployeeDTO) obj;
            Employee employee = new Employee();
            int id = dto.getId();
            LOGGER.info("id: " + id);
            employee.setId(id);
            employee.setLogin(dto.getLogin());
            employee.setFirstName(dto.getFirstName());
            employee.setLastName(dto.getLastName());
            employee.setRoles(dto.getRoles().toString());
            employee.setPhoneNumber1(dto.getPhoneNumber1());
            employee.setPhoneNumber2(dto.getPhoneNumber2());
            employee.setStartDate(dto.getStartDate());
            employee.setLastConnection(dto.getLastConnection());

            newList.add(employee);
        }
        return newList;
    }
}
