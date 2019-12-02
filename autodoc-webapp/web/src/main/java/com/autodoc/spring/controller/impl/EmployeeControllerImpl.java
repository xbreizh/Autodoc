package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.spring.controller.contract.EmployeeController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        employees.remove(0);
        System.out.println("employees: " + employees.size());
        System.out.println("emp: " + employees.get(0));
        mv.addObject("employees", employees);
        return mv;
    }


}

