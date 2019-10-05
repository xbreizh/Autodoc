package com.autodoc.controllers.impl;

import com.autodoc.business.contract.CarModelManager;
import com.autodoc.model.models.car.CarModel;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/carModel")
public class CarModelControllerImpl {

    private Logger logger = Logger.getLogger(CarModelControllerImpl.class);
    private CarModelManager carModelManager;

    public CarModelControllerImpl(CarModelManager carModelManager) {
        this.carModelManager = carModelManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<CarModel> list = carModelManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }


    public String convertObjectIntoGsonObject(Object list) {
        return new Gson().toJson(list);
    }
}
