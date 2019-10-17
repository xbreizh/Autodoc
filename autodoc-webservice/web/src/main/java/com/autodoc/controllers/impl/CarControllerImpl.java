package com.autodoc.controllers.impl;


import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
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
        logger.info("trying to get list of cars");
        List<Car> list = carManager.getAll();
        logger.info("list: " + list);
        String response = converter.convertObjectIntoGsonObject(list);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/getByRegistration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCarByRegistration(@RequestBody String registration) {
        Car car = carManager.getByRegistration(registration);
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/getById/{carId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getById(@PathVariable Integer carId) {
        System.out.println("getting id: " + carId);
        Car car = (Car) carManager.getById(carId);
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity getByClient(String clientLastName, String clientFirstName) {
        return null;
    }

    @Override
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Car car) {
        System.out.println("trying to add a car");
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
    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Car car) {

        logger.debug("trying to update a car: " + car);
        String response = carManager.update(car);
        if (response.equals("car updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    @PutMapping(value = "/updateClient/{carId}/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@PathVariable Integer carId, @PathVariable Integer clientId) {
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
    @DeleteMapping(value = "/delete/{carId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Integer carId) {
        return null;
    }


}
