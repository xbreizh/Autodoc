package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.*;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.dtos.bill.BillForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.tasks.Task;
import com.autodoc.spring.controller.contract.BillController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    BillManager billManager;
    TaskManager taskManager;

    public BillControllerImpl(LibraryHelper helper, BillManager manager, ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager, BillManager billManager, TaskManager taskManager) {
        super(helper);
        this.manager = manager;
        this.billManager = billManager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
        this.taskManager = taskManager;
    }


    @GetMapping("")
    public ModelAndView bills() throws Exception {
        LOGGER.info("retrieving bills");
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills");
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
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_details");
        LOGGER.info("bill is null");
        Bill bill = (Bill) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("bill: " + bill);
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = carManager.getAll(token);
        for (Car car : cars) System.out.println("reg: " + car.getRegistration());
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("billForm", bill);
        mv.addObject("showForm", 1);
        mv.addObject("bill", bill);
        List<Task> taskList = taskManager.getAll(helper.getConnectedToken());
        LOGGER.info("tasks: " + taskList);
        mv.addObject("taskList", taskList);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid BillForm billForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update bill " + billForm);
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_details");
        mv.addObject("billForm", new BillForm());
        List<Task> taskList = taskManager.getAll(helper.getConnectedToken());
        LOGGER.info("tasks: " + taskList);
        mv.addObject("taskList", taskList);
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

  /*  @PostMapping(value = "/balako")
    public ModelAndView getCreateForm(@Valid NewBillForm newBillForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");
        Client client = (Client) clientManager.getById(helper.getConnectedToken(), newBillForm.getClientId());
        Car car = carManager.getByRegistration(helper.getConnectedToken(), newBillForm.getRegistration());
        mv.addObject("car", car);
        mv.addObject("client", client);
        mv.addObject("billForm", new BillForm());
        mv.addObject("showForm", 1);
        return mv;
    }*/

    @GetMapping(value = "/new")
    public ModelAndView getCreateForm() throws Exception {
        String token = helper.getConnectedToken();
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");
        BillForm billForm = new BillForm();
        /*List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Task());
        }
        billForm.setTasks(tasks);*/

        mv.addObject("billForm", billForm);
      /*  TaskList taskList = new TaskList();
        for (Object task1: taskManager.getAll(helper.getConnectedToken())){
            Task t = (Task)task1;
            taskList.addTask(t);
        }*/
        List<Task> taskList = taskManager.getAll(helper.getConnectedToken());
        LOGGER.info("tasks: " + taskList);
        mv.addObject("taskList", taskList);
        //TODO remove client and car patches
        List<Car> cars = carManager.getAll(token);
        String employeeLogin = helper.getConnectedLogin();
        LOGGER.info("getting login: " + employeeLogin);
        billForm.setEmployeeLogin(employeeLogin);
        billForm.setCarRegistration(cars.get(0).getRegistration());
        billForm.setStatus("PENDING_PAYMENT");
        billForm.setVat(19.6);
        List<Client> clients = clientManager.getAll(token);
        billForm.setClientId(clients.get(0).getId());
      /*  mv.addObject("clientId", cars.get(0).getClient().getId());
        mv.addObject("carId", cars.get(0).getId());*/
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid BillForm billForm, BindingResult bindingResult, Model model) throws Exception {
        LOGGER.info("tasks: " + billForm.getTasks().getList());
        LOGGER.info("trying to create bill " + billForm);
        LOGGER.info("tasks: " + billForm.getTasks());
        LOGGER.info("tasks before: " + billForm.getTasks().getList().size());

        LOGGER.info("tasks after: " + billForm.getTasks().getList().size());
        LOGGER.info("tasks: " + billForm.getTasks());
        // Task task = billForm.getTasks().getList().get(0);
        // LOGGER.info(task.getName());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");
        mv.addObject("billForm", new BillForm());
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = carManager.getAll(token);
        billForm.setStatus("PENDING_PAYMENT");
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        List<Task> taskList = taskManager.getAll(helper.getConnectedToken());
        LOGGER.info("tasks: " + taskList);
        mv.addObject("tasks", taskList);
        String employeeLogin = helper.getConnectedLogin();
        LOGGER.info("getting login: " + employeeLogin);
        billForm.setEmployeeLogin(employeeLogin);
        billForm.setCarRegistration(cars.get(0).getRegistration());


        billForm.setClientId(clients.get(0).getId());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors: " + bindingResult.toString());
            LOGGER.error("binding has errors: " + bindingResult.getGlobalError());
            LOGGER.error("binding has errors: " + bindingResult.getObjectName());
            mv.addObject("billForm", billForm);
            mv.addObject("showForm", 1);

            return mv;
        }
        LOGGER.info("bill retrieved: " + billForm);
        manager.add(helper.getConnectedToken(), billForm);
        return bills();
    }


}

