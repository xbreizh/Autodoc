package com.autodoc.controllers.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.controllers.contract.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarControllerImpl implements CarController {
    private Logger logger = Logger.getLogger(CarControllerImpl.class);
    private CarManager carManager;

    private GsonConverter converter;

    public CarControllerImpl(CarManager carManager) {
        if(converter==null)converter = new GsonConverter();
        this.carManager = carManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Car> list = carManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }

    @Override
    @GetMapping(value = "/getByRegistration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Car getCarByRegistration(String registration) {
        return carManager.getByRegistration(registration);
    }

    @Override
    public String getCarById(int id) {
        return null;
    }

    @Override
    public String getCarByClient(String clientLastName, String clientFirstName) {
        return null;
    }

    @Override
    @PostMapping(value = "/addCar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCar(@RequestBody Car car) {
        carManager.save(car);


        return "index";
    }





    @Override
    public String updateCar() {


        return null;
    }

    @Override
    public String updateCarClient(Client client) {
        return null;
    }

    @Override
    public String deleteCar(int carId) {
        return null;
    }




    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        return null;
    }


    @RequestMapping(value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value = "/add/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add(@PathVariable String name) {

        System.out.println("name: " + name);
        return null;
    }


}
