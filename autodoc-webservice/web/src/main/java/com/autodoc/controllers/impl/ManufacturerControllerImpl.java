package com.autodoc.controllers.impl;


import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerControllerImpl implements ManufacturerController {
    private Logger logger = Logger.getLogger(ManufacturerControllerImpl.class);
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;

    public ManufacturerControllerImpl(ManufacturerManager manufacturerManager) {
        if(converter==null)converter = new GsonConverter();
        this.manufacturerManager = manufacturerManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Manufacturer> list = manufacturerManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }


}
