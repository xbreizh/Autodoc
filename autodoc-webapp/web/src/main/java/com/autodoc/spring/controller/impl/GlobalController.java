package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.BillManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.business.contract.GlobalManager;
import com.autodoc.business.exceptions.ObjectFormattingException;
import com.autodoc.helper.contract.AuthenticationHelper;
import com.autodoc.model.dtos.RegistrationForm;
import com.autodoc.model.dtos.car.SearchCarForm;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class GlobalController<T, D, F> {
    private static final String LOGIN = "login";
    private static final String HOME = "home";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final Logger LOGGER = Logger.getLogger(GlobalController.class);
    AuthenticationHelper authenticationHelper;
    @Inject
    BillManager billManager;
    GlobalManager manager;
    @Inject
    private EmployeeManager employeeManager;


    @Inject
    public GlobalController(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
    }

    String getKeyWord() {
        return "";
    }

    protected ModelAndView getById(int id) throws Exception {
        String keyWord = getKeyWord();
        LOGGER.info("trying to get " + keyWord + " with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord + "_details");
        T obj = (T) manager.getById(authenticationHelper.getConnectedToken(), id);
        mv.addObject("form", obj);
        mv.addObject("showForm", 1);
        mv.addObject("obj", obj);
        LOGGER.info("obj: " + obj);
        return mv;
    }


    protected ModelAndView getList() throws Exception {
        String keyWord = getKeyWord();
        LOGGER.info("retrieving " + keyWord);
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord);

        List<T> all = (List<T>) manager.getAll(authenticationHelper.getConnectedToken());

        if (all.isEmpty()) {
            LOGGER.error("nothing to return");
            return sendError(mv, "no " + keyWord + " found");
        }

        mv.addObject(keyWord, all);
        LOGGER.info("mv: " + mv);
        return mv;
    }

    public ModelAndView updateObject(F form, int id, BindingResult bindingResult) throws Exception {
        String keyWord = getKeyWord();
        ModelAndView mv = checkAndAddConnectedDetails(keyWord + "/" + keyWord + "_details");
        if (bindingResult.hasErrors()) {
            addingErrorsToView(bindingResult, mv);
            resettingUpdateViewElements(form, id, mv);
            return mv;
        }
        try {
            manager.update(authenticationHelper.getConnectedToken(), form);
        } catch (ObjectFormattingException e) {
            String error = e.getMessage();
            LOGGER.error("error found: " + error);
            mv.addObject("error", error);
            resettingUpdateViewElements(form, id, mv);
            return mv;
        }
        return new ModelAndView("redirect:" + "/" + keyWord + "/" + id);
    }

    void addingErrorsToView(BindingResult bindingResult, ModelAndView mv) {
        LOGGER.error("binding has " + bindingResult.getAllErrors().size() + " error(s)");
        List<String> errors = new ArrayList<>();
        for (ObjectError obj : bindingResult.getAllErrors()) {
            errors.add(obj.getDefaultMessage());
            FieldError fError = (FieldError) obj;
            LOGGER.error(fError.getObjectName());
            LOGGER.error(fError.getField());
            LOGGER.error(fError.getDefaultMessage());
            LOGGER.error("error found:" + obj.getDefaultMessage());
            LOGGER.error("code found:" + obj.getCode());
            LOGGER.error("code found:" + obj.getClass());
            LOGGER.error("object found:" + obj.getObjectName());
        }
        mv.addObject("errors", errors);
    }

    public void resettingUpdateViewElements(F form, int id, ModelAndView mv) throws Exception {
        T obj = (T) manager.getById(authenticationHelper.getConnectedToken(), id);
        LOGGER.info("resetting update view elements: " + obj);
        mv.addObject("obj", obj);
        mv.addObject("form", form);
        mv.addObject("showForm", 0);
        resettingSpecificElements(mv);
    }

    protected void resettingSpecificElements(ModelAndView mv) throws Exception {
        LOGGER.info("nothing specific to add");
    }


    protected void getPricePerHour(ModelAndView mv) {
        LOGGER.info("getting price per hour");
        mv.addObject("pricePerHour", billManager.getPricePerHour());
    }

    public void setAuthenticationHelper(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
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
        String login = authenticationHelper.getConnectedLogin();
        ModelAndView mv = new ModelAndView(LOGIN);

        // check if user already logged in
        if (!login.equals("anonymousUser")) {
            mv.setViewName(REDIRECT_HOME);
        }

        return mv;
    }


    @GetMapping("/operations")
    public ModelAndView operations(SearchCarForm form) {
        LOGGER.info("show form");
        return checkAndAddConnectedDetails("operations/operations");
    }

    @GetMapping("/person")
    public String showForm(RegistrationForm personForm) {
        LOGGER.info("show form");
        return "operations/operations";
    }

    public ModelAndView checkAndAddConnectedDetails(String viewName) {
        ModelAndView mv = new ModelAndView(viewName);
        Employee connected = employeeManager.getByLogin(authenticationHelper.getConnectedToken(), authenticationHelper.getConnectedLogin());
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

