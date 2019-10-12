package com.autodoc.controllers.helper;

import com.google.gson.Gson;

import javax.inject.Named;

@Named
public class GsonConverter {


    public GsonConverter() {
    }


    public String convertObjectIntoGsonObject(Object list) {
        System.out.println("list to convert: "+list);
        String convertedObject = new Gson().toJson(list);
        System.out.println("object: "+convertedObject);
        return convertedObject;
    }
}
