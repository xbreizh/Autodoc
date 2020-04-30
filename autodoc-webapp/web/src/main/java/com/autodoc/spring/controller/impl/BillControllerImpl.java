package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.*;
import com.autodoc.helper.contract.AuthenticationHelper;
import com.autodoc.helper.contract.BillPdfCreator;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
    BillPdfCreator pdfCreator;

    public BillControllerImpl(AuthenticationHelper authenticationHelper, BillManager manager, PieceManager pieceManager,
                              ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager, TaskManager taskManager, BillPdfCreator pdfCreator) {
        super(authenticationHelper);
        this.manager = manager;
        this.pieceManager = pieceManager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
        this.taskManager = taskManager;
        this.pdfCreator = pdfCreator;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }

    @GetMapping("")
    public ModelAndView bills() throws Exception {
        ModelAndView mv = getList();
        getBillsInProgress(mv);
        return mv;
    }

    @GetMapping("/pdf/{id}")
    public ModelAndView createPDF(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get pdf");
        Bill bill = (Bill) manager.getById(authenticationHelper.getConnectedToken(), id);
        LOGGER.info("bill found: " + bill);
        String newFile = "";
        if (bill == null) {
            LOGGER.error("couldn't find the bill: " + id);
            return new ModelAndView("home");
        }
        newFile = pdfCreator.generatePDFFromHTML(bill);
        return new ModelAndView("bills/pdf/" + newFile);

    }

    @GetMapping("/old")
    public ModelAndView oldBills() throws Exception {
        ModelAndView mv = getList();
        getClosedBills(mv);
        return mv;
    }

    private void getBillsInProgress(ModelAndView mv) {
        List<Bill> list = (List<Bill>) mv.getModel().get(KEY_WORD);
        List<Bill> billsInProgress = new ArrayList<>();
        for (Bill bill : list) {
            if (bill.getStatus().equalsIgnoreCase("PENDING_PIECES") || bill.getStatus().equalsIgnoreCase("PENDING_PAYMENT"))
                billsInProgress.add(bill);
        }
        mv.addObject("billsInProgress", billsInProgress);
    }

    private void getClosedBills(ModelAndView mv) {
        List<Bill> list = (List<Bill>) mv.getModel().get(KEY_WORD);
        List<Bill> closedBills = new ArrayList<>();
        for (Bill bill : list) {
            if (bill.getStatus().equalsIgnoreCase("cancelled") || bill.getStatus().equalsIgnoreCase("COMPLETED"))
                closedBills.add(bill);
        }
        mv.addObject("closedBills", closedBills);
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView billById(@PathVariable Integer id) throws Exception {
        LOGGER.info("getting bill by id: " + id);
        String token = authenticationHelper.getConnectedToken();
        ModelAndView mv = getById(id);
        addingCalculation(mv);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);
        addPieces(token, mv);
        addTasks(token, mv);
        addPaymentType(token, mv);
        addStatus(token, mv);
        Bill bill = (Bill) billManager.getById(token, id);
        if (bill.getStatus() == null) bill.setStatus("PENDING_PAYMENT");
        getPricePerHour(mv);
        mv.addObject("testDate", new Date());
        LOGGER.info("bill: " + bill);

        return mv;
    }

    private void addingCalculation(ModelAndView mv) {
        LOGGER.info("adding calculation");
        mv.addObject("vat", billManager.getVat());
        try {
            Bill bill = (Bill) mv.getModel().get("obj");
            double totalPieces = 0;
            if (bill.getPieces() != null && !bill.getPieces().isEmpty()) {
                for (Piece p : bill.getPieces()) {
                    totalPieces += p.getSellPrice();
                }
            }
            mv.addObject("totalPieces", totalPieces);
            double totalTasks = 0;
            if (bill.getTasks() != null && !bill.getTasks().isEmpty()) {
                for (Task t : bill.getTasks()) {
                    totalTasks += (t.getEstimatedTime() * billManager.getPricePerHour());
                }
            }
            mv.addObject("totalTasks", totalTasks);
            double grandTotalBeforeTaxAndDiscount = totalPieces + totalTasks;
            mv.addObject("grandTotalBeforeTaxAndDiscount", grandTotalBeforeTaxAndDiscount);
         /*   (grandTotal)-
                    ((grandTotalBeforeTaxAndDiscount)*(obj.discount/100))+
                    ((grandTotal)*(vat/100))*/
            double grandTotal = grandTotalBeforeTaxAndDiscount -
                    (grandTotalBeforeTaxAndDiscount) * (bill.getDiscount() / 100) +
                    (grandTotalBeforeTaxAndDiscount) * (bill.getVat() / 100);

            mv.addObject("grandTotal", grandTotal);
        } catch (NullPointerException e) {
            LOGGER.info("nothing to be added");
        }
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid BillForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update bill " + form);
        String token = authenticationHelper.getConnectedToken();
        if (form == null) form = new BillForm();
        ModelAndView mv = updateObject(form, form.getId(), bindingResult);
        mv.addObject("form", form);
        addPaymentType(token, mv);
        addStatus(token, mv);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);
        addPieces(token, mv);
        addTasks(token, mv);
        getPricePerHour(mv);
        addingCalculation(mv);
        return mv;

    }

    @Override
    public void resettingSpecificElements(ModelAndView mv) throws Exception {
        LOGGER.info("adding the specifics");
        String token = authenticationHelper.getConnectedToken();
        addingCalculation(mv);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);
        addPieces(token, mv);
        addTasks(token, mv);
        addPaymentType(token, mv);
        addStatus(token, mv);
        getPricePerHour(mv);
        mv.getModel().forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
        LOGGER.info("printing the map");
    }

    private void addStatus(String token, ModelAndView mv) {
        mv.addObject("statuses", billManager.getStatus(token));
    }

    private void addPaymentType(String token, ModelAndView mv) {
        mv.addObject("paymentTypes", billManager.getPaymentType(token));
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(authenticationHelper.getConnectedToken(), id);
        return bills();
    }


    @GetMapping(value = "/new")
    public ModelAndView getCreateForm(@RequestParam(required = false) int id) throws Exception {

        String token = authenticationHelper.getConnectedToken();
        String login = authenticationHelper.getConnectedLogin();
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
        form.setDiscount(0);
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
        String token = authenticationHelper.getConnectedToken();
        if (form.getDateReparation() == null) LOGGER.error("date shouldn't be null");


        LOGGER.info("date before: " + form.getDateReparation());
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");

        addTasks(token, mv);
        addPieces(token, mv);
        if (form == null) form = new BillForm();
        mv.addObject("form", form);
        addCars(token, mv);
        addClients(token, mv);
        addEmployees(token, mv);

        form.setStatus("PENDING_PAYMENT");

        getPricePerHour(mv);
        LOGGER.info("bill retrieved: " + form);

        if (bindingResult.hasErrors()) {
            addingErrorsToView(bindingResult, mv);
            return mv;
        }
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
        List<Task> taskList = taskManager.getAll(token);
        List<Task> sortedList = taskList.stream().sorted(Comparator.comparing(Task::getId)).collect(Collectors.toList());
        mv.addObject("taskList", sortedList);
    }

    private void addClients(String token, ModelAndView mv) throws Exception {
        mv.addObject("clients", clientManager.getAll(token));
    }


    private void addPieces(String token, ModelAndView mv) throws Exception {
        List<Piece> pieceList = pieceManager.getAll(token);
        List<Piece> sortedList = pieceList.stream().sorted(Comparator.comparing(Piece::getId)).collect(Collectors.toList());
        mv.addObject("pieceList", sortedList);
    }

    private void addEmployees(String token, ModelAndView mv) throws Exception {
        mv.addObject("employees", employeeManager.getAll(token));
    }

    private void addCars(String token, ModelAndView mv) throws Exception {
        mv.addObject("cars", carManager.getAll(token));
    }

}

