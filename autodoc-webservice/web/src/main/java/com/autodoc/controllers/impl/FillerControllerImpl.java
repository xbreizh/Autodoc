package com.autodoc.controllers.impl;


import com.autodoc.business.filler.Filler;
import com.autodoc.controllers.helper.GsonConverter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class FillerControllerImpl {
    private static final Logger LOGGER = Logger.getLogger(FillerControllerImpl.class);
    @Inject
    private Filler filler;

    @Inject
    private GsonConverter converter;


    @GetMapping(value = "/filler",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity fill() throws Exception {
        LOGGER.info("testing LOGGER");
        LOGGER.debug("trying to fill");
        filler.fill();

        System.out.println();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Filling ok");
    }


}
