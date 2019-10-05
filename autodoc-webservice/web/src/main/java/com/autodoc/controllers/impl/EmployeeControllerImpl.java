package com.autodoc.controllers.impl;


import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.controllers.contract.ClientController;
import com.autodoc.controllers.contract.EmployeeController;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.google.gson.Gson;
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

    public EmployeeControllerImpl(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Client> list = employeeManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

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


    public String convertObjectIntoGsonObject(Object list) {
        return new Gson().toJson(list);
    }


}
