package com.autodoc.controllers.helper;

import com.autodoc.business.filler.ManufacturerFiller;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class GsonConverter {
    private Logger logger = Logger.getLogger(GsonConverter.class);

    public GsonConverter() {
    }


    public String convertObjectIntoGsonObject(Object list) {
        logger.debug("list to convert: "+list);
        String convertedObject = new Gson().toJson(list);
        logger.debug("object: "+convertedObject);
        return convertedObject;
    }
}
