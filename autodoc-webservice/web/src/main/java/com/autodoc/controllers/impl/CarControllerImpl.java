package com.autodoc.controllers.impl;


import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarControllerImpl implements CarController {
    private Logger logger = Logger.getLogger(CarControllerImpl.class);
    private CarManager carManager;

    private GsonConverter converter;

    public CarControllerImpl(CarManager carManager) {
        if (converter == null) converter = new GsonConverter();
        this.carManager = carManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {

        List<Car> list = carManager.getAll();
        System.out.println("list: "+list);
        String response = converter.convertObjectIntoGsonObject("car added");

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
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car) {

        System.out.println("trying to add a car: "+car);
        String response = carManager.save(car);
        if (response.equals("car added")){
            return ResponseEntity.ok("car added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

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

   /* @RequestMapping(value = "/add/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add(@PathVariable Car car) {

        System.out.println("name: " + car);
        String df = carManager.save(car);
        System.out.println("df: "+df);
        return df;
    }*/


}
