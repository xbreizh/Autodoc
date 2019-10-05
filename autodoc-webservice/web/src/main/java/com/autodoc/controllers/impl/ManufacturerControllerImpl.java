package com.autodoc.controllers.impl;


import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.model.models.car.Manufacturer;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerControllerImpl implements ManufacturerController {
    private Logger logger = Logger.getLogger(ManufacturerControllerImpl.class);
    private ManufacturerManager manufacturerManager;

    public ManufacturerControllerImpl(ManufacturerManager manufacturerManager) {
        this.manufacturerManager = manufacturerManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Manufacturer> list = manufacturerManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }

    public String convertObjectIntoGsonObject(Object list) {
        return new Gson().toJson(list);
    }

}
