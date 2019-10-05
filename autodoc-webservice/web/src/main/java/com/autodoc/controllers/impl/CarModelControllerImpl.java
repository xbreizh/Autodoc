package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.controllers.contract.CarModelController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

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
}
