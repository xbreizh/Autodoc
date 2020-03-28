package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.*;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.dtos.bill.BillForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import com.autodoc.spring.controller.contract.BillController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ControllerAdvice
@RequestMapping("/bills")
public class BillControllerImpl extends GlobalController<BillDTO, Bill, BillForm> implements BillController {

    private static final Logger LOGGER = Logger.getLogger(BillControllerImpl.class);
    private static final String KEY_WORD = "bills";

    ClientManager clientManager;
    CarManager carManager;
    EmployeeManager employeeManager;
    TaskManager taskManager;
    PieceManager pieceManager;

    public BillControllerImpl(LibraryHelper helper, BillManager manager, PieceManager pieceManager, ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager, TaskManager taskManager) {
        super(helper);
        this.manager = manager;
        this.pieceManager = pieceManager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
        this.taskManager = taskManager;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }

    @GetMapping("")
    public ModelAndView bills() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView billById(@PathVariable Integer id) throws Exception {
        String token = helper.getConnectedToken();
        ModelAndView mv = getById(id);

        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);
        addPieces(token, mv);
        addTasks(token, mv);
        getPricePerHour(mv);

        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid BillForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update bill " + form);
        String token = helper.getConnectedToken();
        if (form == null) form = new BillForm();
        ModelAndView mv = updateObject(form, form.getId(), bindingResult);
        mv.addObject("form", form);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);
        addPieces(token, mv);
        addTasks(token, mv);
        getPricePerHour(mv);
        return mv;

    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return bills();
    }


    @GetMapping(value = "/new")
    public ModelAndView getCreateForm(@RequestParam(required = false) int id) throws Exception {

        String token = helper.getConnectedToken();
        String login = helper.getConnectedLogin();
        LOGGER.info("getting create newForm");
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");
        BillForm form = new BillForm();
        Car car = (Car) carManager.getById(token, id);
        if (car == null) throw new Exception("invalid carId: " + id);
        mv.addObject("car", car);
        form.setCarRegistration(car.getRegistration());
        form.setClientId(car.getClient().getId());
        addTasks(token, mv);
        addPieces(token, mv);
        form.setEmployeeLogin(login);
        form.setStatus("PENDING_PAYMENT");
        form.setVat(19.6);
        mv.addObject("form", form);
        mv.addObject("showForm", 1);

        getPricePerHour(mv);
        LOGGER.info("form: " + form);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid BillForm form, BindingResult bindingResult, Model model) throws Exception {
        LOGGER.info("trying to create bill " + form);
        String token = helper.getConnectedToken();
        if (form.getDateReparation() == null) LOGGER.error("date shouldn't be null");
        if (form.getPieces() == null && form.getTasks() == null) {
            String error = "there should be at least one piece or one task";
            bindingResult.addError(new ObjectError("pieces", error));
            bindingResult.addError(new ObjectError("tasks", error));
        }
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");

        List<Task> taskList = taskManager.getTemplates(token);
        LOGGER.info("tasks: " + taskList);
        addTasks(token, mv);
        addPieces(token, mv);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors: " + bindingResult.getGlobalError());
            mv.addObject("form", form);
            mv.addObject("showForm", 1);
            mv.addObject("error", bindingResult.getGlobalError());
            return mv;
        }
        if (form == null) form = new BillForm();
        mv.addObject("form", form);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);

        form.setStatus("PENDING_PAYMENT");

        getPricePerHour(mv);
        LOGGER.info("bill retrieved: " + form);

        String feedback = manager.add(token, form);
        try {
            int id = Integer.parseInt(feedback);
            return new ModelAndView("redirect:" + "/" + KEY_WORD + "/" + id);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            mv.addObject("error", feedback);
        }
        return mv;
    }

    private void addTasks(String token, ModelAndView mv) throws Exception {
        List<Task> taskList = taskManager.getTemplates(token);
        List<Task> sortedList = taskList.stream().sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
        mv.addObject("taskList", sortedList);
    }

    private void addClients(String token, ModelAndView mv) throws Exception {
        mv.addObject("clients", clientManager.getAll(token));
    }


    private void addPieces(String token, ModelAndView mv) throws Exception {
        List<Piece> pieceList = pieceManager.getAll(token);
        List<Piece> sortedList = pieceList.stream().sorted(Comparator.comparing(Piece::getId)).collect(Collectors.toList());
        System.out.println("pieceList: " + pieceList);
        System.out.println("sortedList: " + sortedList);
        mv.addObject("pieceList", sortedList);
    }

    private void addEmployees(String token, ModelAndView mv) throws Exception {
        mv.addObject("employees", employeeManager.getAll(token));
    }

    private void addCars(String token, ModelAndView mv) throws Exception {
        mv.addObject("cars", carManager.getAll(token));
    }

}

