package com.autodoc.model.models.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals("Manufacturer{id=0, name='JOHN', carModels=0}", manufacturer.toString());
    }

  /*  @Test
    void testListToString() {
        manufacturer.setName("John");
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("PAUL"));
        manufacturers.add(new Manufacturer("JACQUES"));
        assertEquals("[Manufacturer{id=0, name='PAUL', carModels=0}, Manufacturer{id=0, name='JACQUES', carModels=0}]", manufacturers.toString());
        String convertedObject = new Gson().toJson(manufacturers);
        //LOGGER.debug("cc: " + convertedObject);
    }*/
}