package com.autodoc.controllers;

import com.autodoc.business.contract.CarManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    private Logger logger = Logger.getLogger(IndexController.class);

    private CarManager carManager;

    public IndexController(CarManager carManager) {
        this.carManager = carManager;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        logger.info("getting to the Index");

        return "index";
    }


}