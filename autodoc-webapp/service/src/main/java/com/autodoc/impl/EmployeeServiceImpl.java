package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<EmployeeDTO> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return EmployeeDTO.class;
    }


}

