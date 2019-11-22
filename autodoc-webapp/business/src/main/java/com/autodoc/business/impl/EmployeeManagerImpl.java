package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.model.models.Employee;
import com.autodoc.model.dtos.EmployeeDTO;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeManagerImpl extends GlobalManagerImpl<Employee, EmployeeDTO> implements EmployeeManager {

    @Inject
    EmployeeService service;
    public EmployeeManagerImpl(EmployeeService service) {
        super(service);
        this.service=service;
    }

    @Override
    public Employee getByLogin(String token, String login) {
        System.out.println("service: "+service);
        return (Employee) service.getByName(token, login);
    }
}
