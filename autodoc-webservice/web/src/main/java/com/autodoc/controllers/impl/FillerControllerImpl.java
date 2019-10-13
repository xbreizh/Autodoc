package com.autodoc.controllers.impl;


import com.autodoc.business.filler.ManufacturerFiller;
import com.autodoc.controllers.helper.GsonConverter;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
public class FillerControllerImpl {
    private Logger logger = Logger.getLogger(FillerControllerImpl.class);
    @Inject
    private ManufacturerFiller filler;

    @Inject
    private GsonConverter converter;
/*
    public FillerControllerImpl(ManufacturerFiller filler) {
        if (converter == null) converter = new GsonConverter();
        this.filler = filler;
    }*/

    @GetMapping(value = "/filler",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String go() {
        System.out.println("trying to fill");
        filler.fill();
        String response = converter.convertObjectIntoGsonObject("filling ok");

        return response;
    }


}
