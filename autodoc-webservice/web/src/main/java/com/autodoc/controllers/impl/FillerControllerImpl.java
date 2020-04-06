package com.autodoc.controllers.impl;


import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Builder
public class FillerControllerImpl {
    private static final Logger LOGGER = Logger.getLogger(FillerControllerImpl.class);
    private Filler filler;
    private Remover remover;
    private GsonConverter converter;


    @GetMapping(value = "/filler",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity fill() throws Exception {
        LOGGER.info("testing LOGGER");
        LOGGER.debug("trying to fill");
        filler.fill();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Filling ok");
    }

    @GetMapping(value = "/reset",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity reset() throws Exception {
        LOGGER.info("testing LOGGER");
        LOGGER.debug("trying to remove");
        filler.fill();
        remover.cleanup();
        filler.fill();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("removing ok");
    }


}

