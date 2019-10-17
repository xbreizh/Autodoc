package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.controllers.contract.car.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerControllerImpl implements ManufacturerController {
    private Logger logger = Logger.getLogger(ManufacturerControllerImpl.class);
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;

    public ManufacturerControllerImpl(ManufacturerManager manufacturerManager) {
        converter = new GsonConverter();
        this.manufacturerManager = manufacturerManager;
    }


    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {

        List<Manufacturer> list = manufacturerManager.getAll();
        logger.info("list: " + list);
        String response = converter.convertObjectIntoGsonObject(list);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestBody String name) {
        logger.debug("trying to get: " + name);
        Manufacturer manufacturer = manufacturerManager.getByName(name);
        logger.debug("manufacturer: " + manufacturer);
        String response = converter.convertObjectIntoGsonObject(manufacturer);

        return ResponseEntity.ok(response);
    }

}
