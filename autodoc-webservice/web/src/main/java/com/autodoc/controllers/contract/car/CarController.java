package com.autodoc.controllers.contract.car;

import com.autodoc.controllers.contract.GlobalController;
import org.springframework.http.ResponseEntity;

public interface CarController extends GlobalController {


    ResponseEntity getCarByRegistration(String registration);


    ResponseEntity getByClient(String clientLastName, String clientFirstName);

    ResponseEntity updateCarClient(Integer carId, Integer clientId);
}
