package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceImplTest {

    private CarService service;
    private String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDg5MTcwMiwiaWF0IjoxNTc0ODczNzAyfQ.l7uhfrP7qTg4KvhrEjumpZY-14j2o09bw-8PH1usG-vUkfuhmLevrvlLgjWSxZ12kwECA1T_XPZ1ncDOzBVd_w";

    @BeforeEach
    void init(){
        service = new CarServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    @DisplayName("should retuen carDto if registration is valid")
    void getByRegistration() {
        String registration = "05D154875";
       Object obj = service.getByRegistration(token, registration);
       assertAll(
               () -> assertNotNull(obj),
               () -> assertThat(obj, instanceOf(CarDTO.class))
       );
    }

    @Test
    @DisplayName("should return null if invalid registration")
    void getByRegistration1() {
        String registration = "05errrrf";
        Object obj = service.getByRegistration(token, registration);
        assertNull(obj);


    }

    @Test
    void testGetObjectClass() {
    }

    @Test
    void testGetByRegistration() {
    }
}