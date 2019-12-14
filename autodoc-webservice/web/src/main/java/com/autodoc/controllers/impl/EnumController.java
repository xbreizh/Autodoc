package com.autodoc.controllers.impl;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.enums.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("")
public class EnumController {

    private final static Logger LOGGER = Logger.getLogger(EnumController.class);
    protected HttpHeaders responseHeaders;
    private CountryManager manager;
    private GsonConverter converter;

    public EnumController(CountryManager manager) {
        converter = new GsonConverter();
        this.manager = manager;
    }


    @GetMapping(value = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRoles() throws Exception {
        LOGGER.error("getting the roles");
        return returnEnums(Role.class);
    }

    @GetMapping(value = "/compares",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCompares() throws Exception {
        LOGGER.error("getting the compares");
        return returnEnums(Compare.class);
    }

    @GetMapping(value = "/fuelTypes",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFuelTypes() throws Exception {
        LOGGER.error("getting the fuelTypes");
        return returnEnums(FuelType.class);
    }

    @GetMapping(value = "/gearBoxes",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getGearBoxes() throws Exception {
        LOGGER.error("getting the gearBoxes");
        return returnEnums(GearBox.class);
    }

    @GetMapping(value = "/status",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStatus() throws Exception {
        LOGGER.error("getting the status");
        return returnEnums(Status.class);
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
