package com.autodoc.controllers.impl;


import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.controllers.contract.EmployeeController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {
    private Logger logger = Logger.getLogger(EmployeeControllerImpl.class);
    private EmployeeManager employeeManager;
    private GsonConverter converter;

    public EmployeeControllerImpl(EmployeeManager employeeManager) {
        if (converter == null) converter = new GsonConverter();
        this.employeeManager = employeeManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        logger.debug("Called gettho");

        List<Employee> list = employeeManager.getAll();
        logger.debug("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        logger.debug("Returning |" + response + "|");

        return response;
    }


    @Override
    public Employee getEmployeeByName(String name) {
        return null;
    }

    @Override
    public String getEmployeeById(int id) {
        return null;
    }

    @Override
    public String addEmployee(Employee employee) {
        return employeeManager.save(employee);
    }

    @Override
    public String updateEmployee() {
        return null;
    }

    @Override
    public String deleteEmployee(int employeeId) {
        return null;
    }


}
