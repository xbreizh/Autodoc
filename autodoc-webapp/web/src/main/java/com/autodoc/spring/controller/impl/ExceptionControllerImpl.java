package com.autodoc.spring.controller.impl;

import com.autodoc.spring.controller.contract.ExceptionController;
import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceException;

@ControllerAdvice
public class ExceptionControllerImpl implements ExceptionController {
    private static final Logger LOGGER = Logger.getLogger(ExceptionControllerImpl.class);
    private static final String DENIED = "/errors/403";
    private static final String ERROR = "/errors/service";
    private static final String NOT_FOUND = "/errors/404";
    private static final String BAD_REQUEST = "/errors/400";


    public ExceptionControllerImpl() {
    }

    private void logError(HttpServletRequest request, Exception e) {
        LOGGER.error("error: " + e + " / request: " + request.getMethod());
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
        LOGGER.error(request.getMethod());
        return new ModelAndView(ERROR);
    }


    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    @RequestMapping("/denied")
    public ModelAndView error(HttpServletRequest request, Exception e) {
        logError(request, e);
        return new ModelAndView(DENIED);
    }


}

