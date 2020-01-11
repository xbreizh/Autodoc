package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.BillManager;
import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.dtos.bill.BillForm;
import com.autodoc.model.dtos.car.NewBillForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.spring.controller.contract.BillController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/bills")
public class BillControllerImpl extends GlobalController<BillDTO, Bill> implements BillController {

    private static Logger LOGGER = Logger.getLogger(BillControllerImpl.class);
    // @Inject
    BillManager manager;
    ClientManager clientManager;
    CarManager carManager;
    EmployeeManager employeeManager;

    public BillControllerImpl(LibraryHelper helper, BillManager manager, ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager) {
        super(helper);
        this.manager = manager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
    }


    @GetMapping("")
    public ModelAndView bills() throws Exception {
        LOGGER.info("retrieving bills");
        ModelAndView mv = checkAndAddConnectedDetails("bills");
        List<Bill> bills;
        try {
            bills = manager.getAll(helper.getConnectedToken());
            LOGGER.info("bills found: " + bills.size());
            if (bills.isEmpty()) {
                return sendError(mv, "no bill found");
            }
            mv.addObject("bills", bills);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }


        return mv;

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView billById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("bills_details");
        LOGGER.info("bill is null");
        Bill bill = (Bill) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("bill: " + bill);
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = employeeManager.getAll(token);
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("billForm", bill);
        mv.addObject("showForm", 1);
        mv.addObject("bill", bill);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid BillForm billForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + billForm.getId());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("bills_details");
        mv.addObject("billForm", new BillForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Bill bill = (Bill) manager.getById(token, billForm.getId());
            List<Employee> employees = employeeManager.getAll(token);
            List<Client> clients = clientManager.getAll(token);
            List<Car> cars = employeeManager.getAll(token);
            mv.addObject("employees", employees);
            mv.addObject("clients", clients);
            mv.addObject("cars", cars);
            mv.addObject("bill", bill);
            mv.addObject("billForm", billForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("bill retrieved: " + billForm);
        manager.update(helper.getConnectedToken(), billForm);
        return new ModelAndView("redirect:" + "/bills/" + billForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return bills();
    }

    @PostMapping(value = "/balako")
    public ModelAndView getCreate(@Valid NewBillForm newBillForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("bills_new");
        Client client = (Client) clientManager.getById(helper.getConnectedToken(), newBillForm.getClientId());
        Car car = carManager.getByRegistration(helper.getConnectedToken(), newBillForm.getRegistration());
        mv.addObject("car", car);
        mv.addObject("client", client);
        mv.addObject("billForm", new BillForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid BillForm billForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("bills_new");
        LOGGER.info("empl: " + billForm);
        mv.addObject("billForm", new BillForm());
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = employeeManager.getAll(token);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("billForm", billForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("bill retrieved: " + billForm);
        manager.add(helper.getConnectedToken(), billForm);
        return bills();
    }

    private BillDTO convertFormIntoDto(BillForm billForm) {
        LOGGER.info("TODO");
        return null;
    }


}

