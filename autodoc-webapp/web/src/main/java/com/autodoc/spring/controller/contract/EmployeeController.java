package com.autodoc.spring.controller.contract;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface EmployeeController {

    ModelAndView employees(@Valid EmployeeForm employeeForm, BindingResult bindingResult);

    void setEmployeeManager(EmployeeManager employeeManager);
}
