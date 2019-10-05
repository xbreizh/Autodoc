package com.autodoc.controllers.helper;

import com.google.gson.Gson;

import javax.inject.Named;

@Named
public class GsonConverter {


    public GsonConverter() {
    }


    public String convertObjectIntoGsonObject(Object list) {
        return new Gson().toJson(list);
    }
}
