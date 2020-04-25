package com.autodoc.controllers.impl;

import com.autodoc.model.enums.*;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;


@Controller
@Builder
@RequestMapping("")
public class EnumController extends GlobalControllerImpl {

    private final static Logger LOGGER = Logger.getLogger(EnumController.class);


    @GetMapping(value = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRoles() {
        LOGGER.error("getting the roles");
        return returnEnums(Role.class);
    }

    @GetMapping(value = "/compares",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCompares() {
        LOGGER.error("getting the compares");
        return returnEnums(Compare.class);
    }

    @GetMapping(value = "/fuelTypes",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFuelTypes() {
        LOGGER.error("getting the fuelTypes");
        return returnEnums(FuelType.class);
    }

    @GetMapping(value = "/gearBoxes",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getGearBoxes() {
        LOGGER.error("getting the gearBoxes");
        return returnEnums(GearBox.class);
    }

    @GetMapping(value = "/status",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStatus() {
        LOGGER.error("getting the status");
        return returnEnums(Status.class);
    }

    @GetMapping(value = "/paymentTypes",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPaymentTypes() {
        LOGGER.error("getting the paymentTypes");
        return returnEnums(PaymentType.class);
    }


    private ResponseEntity returnEnums(Class cl) {
        List<Object> roles = Collections.singletonList(new ArrayList<Object>(EnumSet.allOf(cl)));
        String response = converter.convertObjectIntoGsonObject(roles);

        ResponseEntity entity = ResponseEntity.ok()
                .headers(responseHeaders)
                .body(response);
        return entity;
    }


}
