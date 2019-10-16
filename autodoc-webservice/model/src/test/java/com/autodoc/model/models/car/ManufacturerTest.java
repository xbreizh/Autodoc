package com.autodoc.model.models.car;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManufacturerTest {

    private Manufacturer manufacturer;

    @BeforeEach
    void init() {
        this.manufacturer = new Manufacturer();
    }

    @Test
    void testToString() {
        manufacturer.setName("John");
        assertEquals("Manufacturer{id=0, name='John'}", manufacturer.toString());
    }

    @Test
    void testListToString() {
        manufacturer.setName("John");
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("PAUL"));
        manufacturers.add(new Manufacturer("JACQUES"));
        assertEquals("[Manufacturer{id=0, name='PAUL'}, Manufacturer{id=0, name='JACQUES'}]", manufacturers.toString());
        String convertedObject = new Gson().toJson(manufacturers);
        //logger.debug("cc: " + convertedObject);
    }
}