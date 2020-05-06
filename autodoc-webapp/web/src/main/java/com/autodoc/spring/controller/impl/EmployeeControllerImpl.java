package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.contract.AuthenticationHelper;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.spring.controller.contract.EmployeeController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeControllerImpl extends GlobalController<Employee, EmployeeDTO, EmployeeForm> implements EmployeeController {

    private static final String KEY_WORD = "employees";
    private static final Logger LOGGER = Logger.getLogger(EmployeeControllerImpl.class);

    public EmployeeControllerImpl(AuthenticationHelper authenticationHelper, EmployeeManager manager) {
        super(authenticationHelper);
        this.manager = manager;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }


    @GetMapping("")
    public ModelAndView employees() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView employeeById(@PathVariable Integer id) throws Exception {
        ModelAndView mv = getById(id);
        addingRoleList(mv);
        return mv;
    }

    private void addingRoleList(ModelAndView mv) {
        LOGGER.info("adding roleList");
        EmployeeManager employeeManager = (EmployeeManager) manager;
        mv.addObject("roleList", employeeManager.getRoles(authenticationHelper.getConnectedToken()));
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid EmployeeForm employeeForm, BindingResult bindingResult) throws Exception {
        if (employeeForm == null) employeeForm = new EmployeeForm();
        if (!manager.checkIfDateIsValid(employeeForm.getStartDate())) {
            addInvalidDateError(bindingResult);

        }
        ModelAndView mv = updateObject(employeeForm, employeeForm.getId(), bindingResult);
        addingRoleList(mv);
        return mv;
    }

    private void addInvalidDateError(BindingResult bindingResult) {
        FieldError fieldError = new FieldError("employeeForm", "startDate", "invalid startDate");
        bindingResult.addError(fieldError);
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(authenticationHelper.getConnectedToken(), id);
        return employees();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("employees/employees_new");
        mv.addObject("employeeForm", new EmployeeForm());
        mv.addObject("showForm", 1);

        addingRoleList(mv);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid EmployeeForm employeeForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("employees/employees_new");
        LOGGER.info("empl: " + employeeForm);
        mv.addObject("employeeForm", new EmployeeForm());
        EmployeeManager employeeManager = (EmployeeManager) manager;
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            addingErrorsToView(bindingResult, mv);
            addingRoleList(mv);
            mv.addObject("employeeForm", employeeForm);
            mv.addObject("showForm", 1);
            mv.addObject("roles", employeeManager.getRoles(authenticationHelper.getConnectedToken()));
            return mv;
        }
        LOGGER.info("employee retrieved: " + employeeForm);
        employeeManager.add(authenticationHelper.getConnectedToken(), employeeForm);
        addingRoleList(mv);
        return new ModelAndView("redirect:/employees");
    }

}

