package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.Employee;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<Employee> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return Employee.class;
    }


}
