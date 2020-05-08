package com.autodoc.model;

import com.autodoc.model.models.car.Car;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    private Car car;

    @BeforeEach
    void init() {
        BasicConfigurator.configure();
        car = new Car();
    }

    @Test
    void testToString() {
        car.setRegistration("ploc");
        car.setId(122);
        //LOGGER.debug(car);
        assertEquals("Car{id=122, registration='PLOC', carModel=null, client=null}", car.toString());

    }
}