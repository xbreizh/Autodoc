package com.autodoc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    private Car car;

    @BeforeEach
    void init(){
        car = new Car();
    }

    @Test
    void testToString() {
        car.setRegistration("ploc");
        car.setId(122);
        assertEquals("Car{id=122, registration='ploc'}", car.toString());

    }
}