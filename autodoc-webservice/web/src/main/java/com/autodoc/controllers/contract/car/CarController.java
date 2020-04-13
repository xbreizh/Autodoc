package com.autodoc.controllers.contract.car;

import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.model.dtos.car.Registration;
import org.springframework.http.ResponseEntity;

public interface CarController extends GlobalController {


    ResponseEntity getByRegistration(String registration);


    ResponseEntity getByClient(String clientLastName, String clientFirstName);

    ResponseEntity updateCarClient(Integer carId, Integer clientId);
}
