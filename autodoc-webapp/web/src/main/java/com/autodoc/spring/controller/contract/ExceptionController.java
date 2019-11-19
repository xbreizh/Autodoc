package com.autodoc.spring.controller.contract;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface ExceptionController {

    ModelAndView handleError404(HttpServletRequest request, Exception e);

    ModelAndView handleError405(HttpServletRequest request, Exception e);

    ModelAndView handleErrorWebServiceException(HttpServletRequest request, Exception e);

    ModelAndView error(HttpServletRequest request, Exception e);


}
