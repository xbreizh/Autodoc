package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.Car;
import com.autodoc.spring.controller.contract.CarController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
@ControllerAdvice
@RequestMapping("/car")
public class CarControllerImpl extends GlobalController implements CarController {
    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    @Inject
    private CarManager carManager;

    public CarControllerImpl(LibraryHelper helper) {
        super(helper);
    }

    @PostMapping("/searchCar")
    public ModelAndView searchCar(String registration) {
        LOGGER.info("retrieving searchCar");
        LOGGER.info("car found: "+registration);
        ModelAndView mv = checkAndAddEmployeeDetails("fragments/searchCar_result");
        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        System.out.println("car found: "+car);
        mv.addObject("car", car);
        return mv;
    }



}

