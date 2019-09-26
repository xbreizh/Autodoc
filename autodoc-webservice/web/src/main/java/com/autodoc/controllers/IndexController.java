package com.autodoc.controllers;

import com.autodoc.business.contract.CarManager;
import com.autodoc.model.models.car.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    private CarManager carManager;

    public IndexController(CarManager carManager) {
        this.carManager = carManager;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        System.out.println("getting here");
        Car car = new Car();

        System.out.println("object: " + car);
        car.setRegistration("reg3");
        carManager.save(car);

        return "indexPage";
    }
}