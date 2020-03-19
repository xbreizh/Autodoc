package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.BillManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.business.contract.GlobalManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.RegistrationForm;
import com.autodoc.model.dtos.car.SearchCarForm;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class GlobalController<T, D, F> {
    private static final String LOGIN = "login";
    private static final String HOME = "home";
    private static final String REDIRECT_HOME = "redirect:/";
    private static Logger LOGGER = Logger.getLogger(GlobalController.class);
    LibraryHelper helper;
    @Inject
    BillManager billManager;
    @Inject
    private ClientManager clientManager;
    @Inject
    private EmployeeManager employeeManager;
   // private PasswordCheckerImpl passwordChecker;
    GlobalManager manager;


    String getKeyWord() {
        return "";
    }


    @Inject
    public GlobalController(LibraryHelper helper) {
        this.helper = helper;
    }

    protected ModelAndView getById(int id) throws Exception {
        String keyWord = getKeyWord();
        LOGGER.info("trying to get " + keyWord + " with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord + "_details");
        LOGGER.info("client is null");
        T obj = (T) manager.getById(helper.getConnectedToken(), id);
        mv.addObject("form", obj);
        mv.addObject("showForm", 1);
        mv.addObject("obj", obj);
        return mv;
    }


    protected ModelAndView getList() throws Exception {
        String keyWord = getKeyWord();
        LOGGER.info("retrieving " + keyWord);
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord);

        List<T> all = (List<T>) manager.getAll(helper.getConnectedToken());

        if (all.isEmpty()) {
            return sendError(mv, "no " + keyWord + " found");
        }

        mv.addObject(keyWord, all);
        return mv;
    }

    public ModelAndView updateObject(F form, int id, BindingResult bindingResult) throws Exception {
        String keyWord = getKeyWord();
        //LOGGER.info("trying to update member with id " + form.getId());
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord + "_details");
        // mv.addObject("form", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            T obj = (T) manager.getById(helper.getConnectedToken(), id);
            mv.addObject("obj", obj);
            mv.addObject("form", form);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("client retrieved: " + form);
        manager.update(helper.getConnectedToken(), form);
        return new ModelAndView("redirect:" + "/" + keyWord + "/" + id);
    }


    protected void getPricePerHour(ModelAndView mv) {
        mv.addObject("pricePerHour", billManager.getPricePerHour());
    }

    public void setHelper(LibraryHelper helper) {
        this.helper = helper;
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    private void logError(HttpServletRequest request, Exception e) {
        LOGGER.error("error: " + e + " / request: " + request.getMethod());
    }

    @RequestMapping("/")
    public ModelAndView home(String error) {
        LOGGER.info("getting home");
        ModelAndView mv = checkAndAddConnectedDetails(HOME);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        String login = helper.getConnectedLogin();
        ModelAndView mv = new ModelAndView(LOGIN);

        // check if user already logged in
        if (!login.equals("anonymousUser")) {
            mv.setViewName(REDIRECT_HOME);
        }

        return mv;
    }


    @GetMapping("/operations")
    public ModelAndView operations(SearchCarForm registrationForm) throws Exception {
        LOGGER.info("show form");
        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        LOGGER.info("clientManager: " + clientManager);
        mv.addObject("clients", clientManager.getAll(helper.getConnectedToken()));
        return mv;
    }

    @GetMapping("/person")
    public String showForm(RegistrationForm personForm) {
        LOGGER.info("show form");
        return "operations/operations";
    }

    @PostMapping("/person")
    public String checkPersonInfo(@Valid RegistrationForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }


    @GetMapping("/repairs")
    public ModelAndView repairs() {
        ModelAndView mv = checkAndAddConnectedDetails("repairs");

        return mv;
    }

    public ModelAndView checkAndAddConnectedDetails(String viewName) {
        ModelAndView mv = new ModelAndView(viewName);
        Employee connected = employeeManager.getByLogin(helper.getConnectedToken(), helper.getConnectedLogin());
        LOGGER.info("connected found: " + connected);
        if (connected == null) mv = new ModelAndView(LOGIN);
        mv.addObject("connected", connected);
        String title = viewName;
        if (viewName.contains("/")) {
            String[] titles = viewName.split("/");
            title = titles[1];
        }
        mv.addObject("pageTitle", title.toUpperCase());
        return mv;
    }


    @GetMapping("/myProfile")
    public ModelAndView myProfile() {
        LOGGER.info("retrieving myProfile");
        ModelAndView mv = checkAndAddConnectedDetails("myProfile");
        return mv;
    }

    ModelAndView sendError(ModelAndView mv, String error) {
        mv.addObject("error", error);
        return mv;
    }


}

