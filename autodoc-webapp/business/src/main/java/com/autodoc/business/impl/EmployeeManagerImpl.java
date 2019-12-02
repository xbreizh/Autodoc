package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.person.employee.Employee;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeManagerImpl extends GlobalManagerImpl<Employee, EmployeeDTO> implements EmployeeManager {

    @Inject
    EmployeeService service;

    public EmployeeManagerImpl(EmployeeService service) {
        super(service);
        this.service = service;
    }

    @Override
    public Employee getByLogin(String token, String login) {
        System.out.println("serviceee: " + service);
        return dtoToEntity(service.getByName(token, login));
    }

    public Employee dtoToEntity(Object obj) {

        EmployeeDTO dto = (EmployeeDTO) obj;
        Employee employee = new Employee();
       // employee.setId(dto.getId());
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setPhoneNumber2(dto.getPhoneNumber2());
        employee.setStartDate(dto.getStartDate());
        employee.setLastConnection(dto.getLastConnection());


        return employee;
    }

}
