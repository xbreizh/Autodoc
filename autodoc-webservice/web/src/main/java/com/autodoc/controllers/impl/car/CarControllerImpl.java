package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;
import javax.validation.ConstraintViolationException;

@Named
@Controller
@RequestMapping("/cars")
@Builder
public class CarControllerImpl extends GlobalControllerImpl<Car, CarDTO> implements CarController {
    private static final Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    private CarManager carManager;

    public IGenericManager getManager() {
        return carManager;
    }


    @Override
    @GetMapping(value = "/registration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByRegistration(@RequestParam(value = "registration") String registration) {
        if (!registration.matches("^[a-zA-Z0-9]{8,10}$")) throw new ConstraintViolationException
                (registration + " : invalid registration (letters, numbers, between 8 and 10)", null);
        System.out.println("after");
        CarDTO car = carManager.getByRegistration(registration);
        if (car == null) return notFoundResponse;
        LOGGER.info("reaching herer");
        System.out.println("here");
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByName(@RequestParam(value = "name") String name) throws Exception {
        LOGGER.error("trying to use a not yet implemented method");
        throw new Exception(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }


    @Override
    public ResponseEntity<String> getByClient(String clientLastName, String clientFirstName) {
        return null;
    }


    @Override
    @PatchMapping(value = "/updateClient/{carId}/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@PathVariable Integer carId, @PathVariable Integer clientId) {
        LOGGER.debug("car id: " + carId + " / client id: " + clientId);
        CarDTO response = carManager.updateClient(carId, clientId);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}
