/*
package com.autodoc.spring.controller;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.helper.PasswordCheckerImpl;
import com.autodoc.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceException;

@Controller
@ControllerAdvice
@RequestMapping("/employees")
public class EmployeeController {
    private static final String LOGIN = "login";
    private static final String HOME = "home";
    private static final String RESET = "passwordReset/passwordReset";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final String DENIED = "/errors/403";
    private static final String ERROR = "/errors/service";
    private static final String NOT_FOUND = "/errors/404";
    private static final String RESET_OK = "passwordReset/passwordResetOk";
    private static final String SEND_EMAIL_OK = "passwordReset/passwordResetLinkOk";
    private static final String RESET_KO = "passwordReset/passwordResetLinkKo";
    private static final String SEND_EMAIL = "passwordReset/passwordResetSendEmail";
    private static Logger logger = Logger.getLogger(EmployeeController.class);
    */
/* private MemberManager memberManager;
     private BookManager bookManager;
     private LoanManager loanManager;*//*

    private EmployeeManager employeeManager;
    private LibraryHelper helper;

    private PasswordCheckerImpl passwordChecker;

   */
/* @Inject
    public UserController(MemberManager memberManager, BookManager bookManager, LoanManager loanManager, LibraryHelper helper, PasswordCheckerImpl passwordChecker) {
        this.memberManager = memberManager;
        this.bookManager = bookManager;
        this.loanManager = loanManager;
        this.helper = helper;
        this.passwordChecker = passwordChecker;
    }*//*


    @Inject
    public EmployeeController(LibraryHelper helper) {
        this.helper = helper;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e) {
        logError(request, e);
        return new ModelAndView(NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleError405(HttpServletRequest request, Exception e) {
        logError(request, e);
        return new ModelAndView(ERROR);
    }

    @ExceptionHandler({WebServiceException.class, NullPointerException.class, TemplateInputException.class})
    public ModelAndView handleErrorWebServiceException(HttpServletRequest request, Exception e) {
        logError(request, e);
        logger.error(request.getMethod());
        return new ModelAndView(ERROR);
    }


    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    @RequestMapping("/denied")
    public ModelAndView error(HttpServletRequest request, Exception e) {
        logError(request, e);
        return new ModelAndView(DENIED);
    }

    private void logError(HttpServletRequest request, Exception e) {
        logger.error("error: " + e + " / request: " + request.getMethod());
    }

    @RequestMapping("/")
    public ModelAndView home(String error) {
        logger.info("getting home");
        System.out.println("employee home");

        String token = helper.getConnectedToken();
        String login = helper.getConnectedLogin();
        Employee employee = employeeManager.getEmployee(token, login);
        System.out.println("employee: "+employee);

        ModelAndView mv = new ModelAndView(HOME);
        if (employee != null) {
            logger.info("Employee retrieved: " + employee);


            helper.addingPopup(mv, error);

            mv.addObject("member", employee);
        }
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



}

*/
