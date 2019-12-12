package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class EmployeeManagerImpl extends GlobalManagerImpl<Employee, EmployeeDTO> implements EmployeeManager {

    private static Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);

    // @Inject
    EmployeeService service;

    public EmployeeManagerImpl(EmployeeService service) {
        super(service);
        this.service = service;
    }

    @Override
    public Employee getByLogin(String token, String login) {
        System.out.println("serviceee: " + service);
        return dtoToEntity(token, service.getByName(token, login));
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
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setPhoneNumber2(dto.getPhoneNumber2());
        employee.setStartDate(dto.getStartDate());
        employee.setLastConnection(dto.getLastConnection());
        LOGGER.info("entity transferred: " + employee);

        return employee;
    }

    public void update(String token, Object obj) {
        LOGGER.info("stuff to update: " + obj);
        EmployeeForm dto = (EmployeeForm) obj;
        LOGGER.info("dto: " + dto);
        LOGGER.info(dto.getFirstName());
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(dto.getId());
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setPhoneNumber2(dto.getPhoneNumber2());
        LOGGER.info("entity transferred: " + employee);
        service.update(token, employee);

    }


}
