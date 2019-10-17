package com.autodoc.controllers.contract.car;

import com.autodoc.model.models.car.Car;
import org.springframework.http.ResponseEntity;

public interface CarController/* extends GlobalController*/ {


    ResponseEntity getAll();

    ResponseEntity getCarByRegistration(String registration);

    ResponseEntity getById(Integer id);

    ResponseEntity getByClient(String clientLastName, String clientFirstName);

    ResponseEntity add(Car car);

    ResponseEntity update(Car car);

    ResponseEntity updateCarClient(Integer carId, Integer clientId);

    ResponseEntity delete(Integer carId);
}
