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
    public ModelAndView employees() {
        LOGGER.info("retrieving employees");
        ModelAndView mv = checkAndAddConnectedDetails("employees");

        List<EmployeeDTO> employees = getEmployees();

        if (employees.isEmpty()) {
            return sendError(mv, "no employee found");
        }

        mv.addObject("employees", employees);
        return mv;

    }

    private List<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> list = (List<EmployeeDTO>) employeeManager.getAll(helper.getConnectedToken());
        return list;
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView employeeById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("employees_details");
        System.out.println("employee is null");
        Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + employee.getPhoneNumber1());
        LOGGER.info("lastC: " + employee.getLastConnection());
        LOGGER.info("startDate: " + employee.getStartDate());
        mv.addObject("employeeForm", employee);
        mv.addObject("showForm", 1);
        mv.addObject("employee", employee);
        mv.addObject("roles", employeeManager.getRoles(helper.getConnectedToken()));
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid EmployeeForm employeeForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + employeeForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("employees_details");
        mv.addObject("employeeForm", new EmployeeForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Employee employee = (Employee) employeeManager.getById(helper.getConnectedToken(), employeeForm.getId());
            mv.addObject("employee", employee);
            mv.addObject("employeeForm", employeeForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("employee retrieved: " + employeeForm);
        employeeManager.update(helper.getConnectedToken(), employeeForm);
        return new ModelAndView("redirect:" + "/employees/" + employeeForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) {
        LOGGER.info("trying to delete member with id " + id);
        employeeManager.delete(helper.getConnectedToken(), id);
        return employees();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("employees_new");
        mv.addObject("employeeForm", new EmployeeForm());
        mv.addObject("showForm", 1);
        mv.addObject("roles", employeeManager.getRoles(helper.getConnectedToken()));
        return mv;
    }



    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("employees_new");
        LOGGER.info("empl: " + employeeForm);
        mv.addObject("employeeForm", new EmployeeForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("employeeForm", employeeForm);
            mv.addObject("showForm", 1);
            mv.addObject("roles", employeeManager.getRoles(helper.getConnectedToken()));
            return mv;
        }
        LOGGER.info("employee retrieved: " + employeeForm);
        employeeManager.add(helper.getConnectedToken(), employeeForm);
        return employees();
        //return new ModelAndView("redirect:" + "/employees/" );
    }

    private EmployeeDTO convertFormIntoDto(EmployeeForm employeeForm) {
        LOGGER.info("TODO");
        return null;
    }


}

