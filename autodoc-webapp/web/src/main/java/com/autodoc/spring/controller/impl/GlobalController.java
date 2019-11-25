package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.helper.PasswordCheckerImpl;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class GlobalController {
    private static final String LOGIN = "login";
    private static final String HOME = "home";
    private static final String RESET = "passwordReset/passwordReset";
    private static final String REDIRECT_HOME = "redirect:/";

    private static final String RESET_OK = "passwordReset/passwordResetOk";
    private static final String SEND_EMAIL_OK = "passwordReset/passwordResetLinkOk";
    private static final String RESET_KO = "passwordReset/passwordResetLinkKo";
    private static final String SEND_EMAIL = "passwordReset/passwordResetSendEmail";
    private static Logger LOGGER = Logger.getLogger(GlobalController.class);

    public GlobalController(LibraryHelper helper, PasswordCheckerImpl passwordChecker) {
        this.helper = helper;
        this.passwordChecker = passwordChecker;
        // this.employeeManager = employeeManager;
    }

    LibraryHelper helper;

    public void setHelper(LibraryHelper helper) {
        this.helper = helper;
    }

    @Inject
    private EmployeeManager employeeManager;
    private PasswordCheckerImpl passwordChecker;

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Inject
    public GlobalController(LibraryHelper helper) {
        this.helper = helper;
    }


    private void logError(HttpServletRequest request, Exception e) {
        LOGGER.error("error: " + e + " / request: " + request.getMethod());
    }

    @RequestMapping("/")
    public ModelAndView home(String error) {
        LOGGER.info("getting home");
        ModelAndView mv = checkAndAddEmployeeDetails(HOME);
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


    @GetMapping("/stocks")
    public ModelAndView stocks() {
        ModelAndView mv = checkAndAddEmployeeDetails("stocks");

        return mv;
    }

    @GetMapping("/operations")
    public ModelAndView operations() {
        ModelAndView mv = checkAndAddEmployeeDetails("operations");

        return mv;
    }

    @GetMapping("/repairs")
    public ModelAndView repairs() {
        ModelAndView mv = checkAndAddEmployeeDetails("repairs");

        return mv;
    }

    ModelAndView checkAndAddEmployeeDetails(String viewName) {
        ModelAndView mv = new ModelAndView(viewName);
        System.out.println("view: " + mv);
        System.out.println(helper.getConnectedToken());
        System.out.println(helper.getConnectedLogin());
        System.out.println("em: " + employeeManager);
        Employee employee = employeeManager.getByLogin(helper.getConnectedToken(), helper.getConnectedLogin());
        System.out.println("employee found: " + employee);
        if (employee == null) mv = new ModelAndView(LOGIN);
        mv.addObject("employee", employee);
        return mv;
    }








/*

    @GetMapping("/passwordReset")
    public ModelAndView passwordReset(String login, String token) {
        ModelAndView mv = new ModelAndView(RESET);
        mv.addObject(LOGIN, login);
        mv.addObject("token", token);

        return mv;
    }

    @PostMapping("/passwordResetSendEmail")
    public ModelAndView passwordResetSendEmail1(String login, String email) throws BusinessExceptionConnect {
        logger.info(LOGIN + " " + login);
        logger.info("email: " + email);
        ModelAndView mv = new ModelAndView();
        mv.addObject(LOGIN, login);
        mv.addObject("email", email);
        if (memberManager.sendResetPasswordLink(login, email)) {

            mv.setViewName(SEND_EMAIL_OK);
        } else {
            mv.setViewName(RESET_KO);
        }
        return mv;
    }

    @GetMapping("/recover")
    public ModelAndView passwordResetSendEmail() {

        return new ModelAndView(SEND_EMAIL);
    }


    @PostMapping("/passwordReset1")
    public ModelAndView passwordReset1(String login, String password, String confirmPassword, String token) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(LOGIN, login);
        mv.addObject("token", token);
        String error = passwordChecker.checkValidity(password, confirmPassword);
        if (!error.isEmpty()) {
            mv.addObject("error", error);
            mv.setViewName(RESET);
            logger.info("View  /d login: " + login + " / password: " + password + " / password2: " + confirmPassword + " / token: " + token);
            return mv;
        } else {
            try {
                memberManager.resetPassword(login, password, token);
            } catch (BusinessExceptionConnect businessExceptionConnect) {
                logger.error(businessExceptionConnect.getMessage());
            }
            return new ModelAndView(RESET_OK);
        }

    }


    @PostMapping("/renew")
    public ModelAndView renew(String id) throws BusinessExceptionLoan {
        String token = helper.getConnectedToken();
        logger.info("trying to renew: " + id);
        int idLoan = Integer.parseInt(id);
        loanManager.renewLoan(token, idLoan);

        return new ModelAndView(REDIRECT_HOME);

    }

    @PostMapping("/reminder")
    public ModelAndView reminder(String login, boolean reminder) throws BusinessExceptionMember {
        String token = helper.getConnectedToken();
        logger.info("updating reminder");
        memberManager.switchReminder(token, login, reminder);

        return new ModelAndView(REDIRECT_HOME);

    }


    @PostMapping("/remove")
    public ModelAndView remove(String loanId) throws BusinessExceptionLoan {
        String token = helper.getConnectedToken();
        logger.info("trying to remove: " + loanId);

        loanManager.removeLoan(token, Integer.parseInt(loanId));

        return new ModelAndView(REDIRECT_HOME);

    }


    @PostMapping("/reserve")
    public ModelAndView reserve(String isbn) {
        logger.info("getting into search");

        String token = helper.getConnectedToken();
        logger.info("isbn received: " + isbn);
        logger.info(loanManager.reserve(token, isbn));

        return new ModelAndView(REDIRECT_HOME);

    }


    @PostMapping("/search")
    public ModelAndView search(String isbn, String author, String title) throws BusinessExceptionBook {
        logger.info("getting into search");
        ModelAndView mv = new ModelAndView(HOME);

        String token = helper.getConnectedToken();
        String login = helper.getConnectedLogin();

        logger.info("elements received: isbn" + isbn + " / title " + title + " / author: " + author);


        Map<String, String> criteria = helper.generateSearchMap(isbn, author, title);
        List<Book> books = bookManager.searchBooks(token, criteria);


        Member member = memberManager.getMember(token, login);
        if (member == null) return new ModelAndView(LOGIN);

        mv.addObject("loanList", member.getLoanList());
        mv.addObject("member", member);
        mv.addObject("books", books);
        mv.addObject("isbn", isbn);
        mv.addObject("title", title);
        mv.addObject("author", author);


        helper.checkOverdue(member, mv);
        helper.getIsbnRentedList(member, mv);
        helper.checkMaxReserved(member, mv);

        return mv;
    }

*/
}

