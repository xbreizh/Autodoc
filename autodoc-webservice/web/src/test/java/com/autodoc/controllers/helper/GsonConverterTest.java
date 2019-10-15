package com.autodoc.controllers.helper;

import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GsonConverterTest {
    GsonConverter converter;
    private Logger logger = Logger.getLogger(GsonConverterTest.class);

    @BeforeEach
    void init() {
        converter = new GsonConverter();
    }

    @Test
    void convertObjectIntoGsonObject() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("AUDI"));
        manufacturers.add(new Manufacturer("BMW"));
        manufacturers.add(new Manufacturer("RENAULT"));
        logger.debug("list: " + manufacturers);

        String converted = converter.convertObjectIntoGsonObject(manufacturers);
        logger.debug("converted: " + converted);


    }
}