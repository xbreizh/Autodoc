package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.controllers.contract.CarModelController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carModel")
public class CarModelControllerImpl implements CarModelController {

    private Logger logger = Logger.getLogger(CarModelControllerImpl.class);
    private CarModelManager carModelManager;
    private GsonConverter converter;

    public CarModelControllerImpl(CarModelManager carModelManager) {
        converter = new GsonConverter();
        this.carModelManager = carModelManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {

        List<CarModel> list = carModelManager.getAll();
        logger.debug("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        logger.debug("Returning |" + response + "|");

        return response;
    }

    @Override
    public String getCarModelsByManufacturer(String manufacturer) {
        return null;
    }

    @GetMapping(value = "/getById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getById(@PathVariable int id) {
        CarModel carModel = carModelManager.getById(id);
        return converter.convertObjectIntoGsonObject(carModel);
    }


    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getByName(@RequestBody String name) {
        logger.debug("getting: " + name);
        CarModel carModel = carModelManager.getByName(name);
        return converter.convertObjectIntoGsonObject(carModel);
    }
}
