package com.autodoc.controllers;


import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.impl.ExampleDAO;
import com.autodoc.model.models.ExampleObject;
import com.autodoc.model.models.car.Car;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
  /*  @Inject
    private ExampleDAO database;*/

    @Inject
    private CarManager carManager;

    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Car> list = carManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }

    private String convertObjectIntoGsonObject(List<Car> list) {
        return new Gson().toJson(list);
    }


    @RequestMapping(value = "/geto",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        /*System.out.println("Called get");

        List<ExampleObject> list = database.get();
        System.out.println("Loaded |" + list + "|");
        String response = new Gson().toJson(database.get());
        System.out.println("Returning |" + response + "|");

        return response;*/
        return null;
    }


    @RequestMapping(value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get(@PathVariable String id) {
       /* System.out.println("Getting |" + id + "|");
        ExampleObject object = database.get(id);
        System.out.println("Loaded |" + object + "|");
        String response = new Gson().toJson(object);
        System.out.println("Returning |" + response + "|");
        return response;*/
       return null;
    }

    @RequestMapping(value = "/add/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add(@PathVariable String name) {
       /* System.out.println("Received |" + name + "|");
        ExampleObject object = new ExampleObject();
        object.setName(name);
        System.out.println("Adding |" + object + "|");
        database.add(object);
        System.out.println("Added |" + object + "|");
        String response = new Gson().toJson(object);
        System.out.println("Returning |" + response + "|");
        return response;*/
       return null;
    }

    public CarManager getCarManager() {
        return carManager;
    }

    public void setCarManager(CarManager carManager) {
        this.carManager = carManager;
    }
}
