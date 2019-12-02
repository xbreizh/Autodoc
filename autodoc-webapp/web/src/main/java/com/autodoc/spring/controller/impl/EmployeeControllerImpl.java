package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.spring.controller.contract.EmployeeController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeControllerImpl extends GlobalController implements EmployeeController {

    // @Inject
    EmployeeManager employeeManager;

    private static Logger LOGGER = Logger.getLogger(EmployeeControllerImpl.class);

    public EmployeeControllerImpl(LibraryHelper helper, EmployeeManager employeeManager) {
        super(helper);
        this.employeeManager = employeeManager;
    }


    @GetMapping("")
    public ModelAndView employees(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        LOGGER.info("retrieving employees");
        ModelAndView mv = checkAndAddEmployeeDetails("employees");
        System.out.println("helper: " + helper.getConnectedToken());
        System.out.println(employeeManager);
        if (bindingResult.hasErrors()) {
            return mv;
        }
//        employeeManager.getAll("gettall: "+helper.getConnectedToken());
        List<EmployeeDTO> employees = employeeManager.getAll(helper.getConnectedToken());
        System.out.println("employee: " + employees.size());
        System.out.println("emp: " + employees.get(0));
        mv.addObject("employees", employees);
        return mv;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView employeeById(@PathVariable Integer id, @Valid EmployeeForm employeeForm) {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddEmployeeDetails("employees_details");
        Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), id);
        mv.addObject("employeeForm", employeeForm);
        //if (employee==null)return mv;
        mv.addObject("employee", employee);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView employeeById(EmployeeForm employeeForm, BindingResult bindingResult) {
        LOGGER.info("trying to update member with id " + employeeForm.getId());
        LOGGER.info("member received: " + employeeForm.getId());
       /* ModelAndView mv = checkAndAddEmployeeDetails("employees_details");
       // Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
        //if (employee==null)return mv;
        EmployeeDTO dto = convertFormIntoDto(employeeForm);
        Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
        LOGGER.info("employee retrieved: "+employee);
        mv.addObject("employeeForm", new EmployeeForm());
        mv.addObject("employee", employee);*/
        ModelAndView mv = new ModelAndView("greeting");
        mv.addObject("employeeForm", employeeForm);
        return mv;
    }

    private EmployeeDTO convertFormIntoDto(EmployeeForm employeeForm) {
        LOGGER.info("TODO");
        return null;
    }

}

