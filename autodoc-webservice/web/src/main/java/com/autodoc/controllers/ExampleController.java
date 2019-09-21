package com.autodoc.controllers;


import com.autodoc.dao.impl.ExampleDAO;
import com.autodoc.model.ExampleObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private ExampleDAO database;


    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        System.out.println("Called get");

        List<ExampleObject> list = database.get();
        System.out.println("Loaded |" + list + "|");
        String response = new Gson().toJson(database.get());
        System.out.println("Returning |" + response + "|");

        return response;
    }


    @RequestMapping(value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get(@PathVariable String id) {
        System.out.println("Getting |" + id + "|");
        ExampleObject object = database.get(id);
        System.out.println("Loaded |" + object + "|");
        String response = new Gson().toJson(object);
        System.out.println("Returning |" + response + "|");
        return response;
    }

    @RequestMapping(value = "/add/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String add(@PathVariable String name) {
        System.out.println("Received |" + name + "|");
        ExampleObject object = new ExampleObject();
        object.setName(name);
        System.out.println("Adding |" + object + "|");
        database.add(object);
        System.out.println("Added |" + object + "|");
        String response = new Gson().toJson(object);
        System.out.println("Returning |" + response + "|");
        return response;
    }
}
