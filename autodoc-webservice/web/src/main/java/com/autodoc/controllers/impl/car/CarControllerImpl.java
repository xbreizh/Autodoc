package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;

@Named
@Controller
@RequestMapping("/cars")
public class CarControllerImpl extends GlobalControllerImpl<Car, CarDTO> implements CarController {
    private static final Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    private CarManager carManager;

    private GsonConverter converter;

    public CarControllerImpl(CarManager carManager) {
        super(carManager);
        if (converter == null) converter = new GsonConverter();
        this.carManager = carManager;
    }


 /*   @GetMapping("/{id}")
    public CarDTO getById(@PathVariable("id") CarDTO carDTO) {
        System.out.println("trying to find by id: ");
        return carDTO;
    }*/


    @Override
    @GetMapping(value = "/registration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByRegistration(@RequestParam(value = "registration") String registration) {
        CarDTO car = carManager.getByRegistration(registration);
        if (car == null) return notFoundResponse;
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestParam(value = "name") String name) throws Exception {
        LOGGER.error("trying to use a not yet implemented method");
        throw new Exception(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }


    @Override
    public ResponseEntity getByClient(String clientLastName, String clientFirstName) {
        return null;
    }


    @Override
    @PatchMapping(value = "/updateClient/{carId}/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@PathVariable Integer carId, @PathVariable Integer clientId) throws Exception {
        LOGGER.debug("car id: " + carId + " / client id: " + clientId);
        CarDTO response = carManager.updateClient(carId, clientId);
        if (response.equals("car updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}
