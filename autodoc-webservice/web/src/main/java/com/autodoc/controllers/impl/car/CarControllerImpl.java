package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.controllers.impl.exceptions.NotImplementedmethodException;
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
    /**
     * @param descMatch Lookup on description field.
     * @param lang Lookup on language.
     */
    public ResponseEntity<String> getByRegistration(@RequestParam(value = "registration") String registration) {
        LOGGER.info("receiving registration: " + registration);
        CarDTO car = carManager.getByRegistration(registration);
        if (car == null) return notFoundResponse;
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByName(@RequestParam(value = "name") String name) throws NotImplementedmethodException {

        throw new NotImplementedmethodException(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }


    @Override
    @GetMapping(value = "/byClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByClient(String clientLastName, String clientFirstName) throws NotImplementedmethodException {
        throw new NotImplementedmethodException(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }

    @Override
    @PutMapping(value = "/updateClient/{carId}/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@PathVariable Integer carId, @PathVariable Integer clientId) {
        LOGGER.info("car id: " + carId + " / client id: " + clientId);
        CarDTO response = carManager.updateClient(carId, clientId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
