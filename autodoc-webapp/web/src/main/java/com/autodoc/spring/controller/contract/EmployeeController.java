package com.autodoc.spring.controller.contract;

import com.autodoc.business.contract.EmployeeManager;
import org.springframework.web.servlet.ModelAndView;

public interface EmployeeController {

    ModelAndView employees() throws Exception;

    void setEmployeeManager(EmployeeManager employeeManager);
}
