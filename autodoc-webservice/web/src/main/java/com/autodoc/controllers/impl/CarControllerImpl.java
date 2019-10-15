package com.autodoc.controllers.impl;


import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
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
    public ResponseEntity getAll() {

        List<Car> list = carManager.getAll();
        logger.debug("list: " + list);
        String response = converter.convertObjectIntoGsonObject("car added");

        if (response.equals("car added")) {
            return ResponseEntity.ok("car added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    @GetMapping(value = "/getByRegistration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCarByRegistration(String registration) {
        Car car = carManager.getByRegistration(registration);
        String response = converter.convertObjectIntoGsonObject(car);
        if (response.equals("car added")) {
            return ResponseEntity.ok("car added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    public ResponseEntity getCarById(int id) {
        return null;
    }

    @Override
    public ResponseEntity getCarByClient(String clientLastName, String clientFirstName) {
        return null;
    }

    @Override
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car) {

        logger.debug("trying to add a car: " + car);
        String response = carManager.save(car);
        if (response.equals("car added")) {
            return ResponseEntity.ok("car added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }


    @Override
    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCar(@RequestBody Car car) {

        logger.debug("trying to update a car: " + car);
        String response = carManager.update(car);
        if (response.equals("car update")) {
            return ResponseEntity.ok("car update");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    @PostMapping(value = "/updateClient",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@RequestBody int carId, @RequestBody int clientId) {
        logger.debug("car id: " + carId + " / client id: " + clientId);
        String response = carManager.updateClient(carId, clientId);
        if (response.equals("car update")) {
            return ResponseEntity.ok("car update");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    public ResponseEntity deleteCar(int carId) {
        return null;
    }


    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        return null;
    }


    @RequestMapping(value = "/getById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        return null;
    }

   /* @RequestMapping(value = "/add/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add(@PathVariable Car car) {

        logger.debug("name: " + car);
        String df = carManager.save(car);
        logger.debug("df: "+df);
        return df;
    }*/


}
