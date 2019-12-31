package com.autodoc.controllers.helper;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class GsonConverter {
    private static final Logger LOGGER = Logger.getLogger(GsonConverter.class);

    public GsonConverter() {
    }


    public String convertObjectIntoGsonObject(Object list) {
        LOGGER.debug("list to convert: " + list);
        LOGGER.info("to convert: " + list);
       /* Gson gsonBuilder = new GsonBuilder()
                .setDateFormat("MM-dd-yyyy").create();
        String convertedObject = gsonBuilder.toJson(list);*/
        String convertedObject = new Gson().toJson(list);
        LOGGER.debug("object: " + convertedObject);
        return convertedObject;
    }



}
