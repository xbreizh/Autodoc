package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;
import org.springframework.http.ResponseEntity;

public interface CarController {


    ResponseEntity getAll();

    ResponseEntity getCarByRegistration(String registration);

    ResponseEntity getCarById(int id);

    ResponseEntity getCarByClient(String clientLastName, String clientFirstName);

    ResponseEntity addCar(Car car);

    ResponseEntity updateCar(Car car);

    ResponseEntity updateCarClient(int carId, int clientId);

    ResponseEntity deleteCar(int carId);
}
