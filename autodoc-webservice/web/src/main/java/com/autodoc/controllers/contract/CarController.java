package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;
import org.springframework.http.ResponseEntity;

public interface CarController {


    ResponseEntity getAll();

    ResponseEntity getCarByRegistration(String registration);

    ResponseEntity getCarById(Integer id);

    ResponseEntity getCarByClient(String clientLastName, String clientFirstName);

    ResponseEntity addCar(Car car);

    ResponseEntity updateCar(Car car);

    ResponseEntity updateCarClient(Integer carId, Integer clientId);

    ResponseEntity deleteCar(Integer carId);
}
