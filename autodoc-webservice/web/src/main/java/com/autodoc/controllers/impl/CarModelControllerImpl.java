package com.autodoc.controllers.impl;

import com.autodoc.business.contract.CarModelManager;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.CarModel;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/carModel")
public class CarModelControllerImpl {

    private Logger logger = Logger.getLogger(CarModelControllerImpl.class);
    private CarModelManager carModelManager;
    private GsonConverter converter;

    public CarModelControllerImpl(CarModelManager carModelManager) {
        if(converter==null)converter = new GsonConverter();
        this.carModelManager = carModelManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<CarModel> list = carModelManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }



}
