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
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk2ODA1MCwiaWF0IjoxNTc0OTUwMDUwfQ.BfKxkfEVLatJRdOfVph7XqJHnCBWPGFKyaq2exJNUYFMr9lU0-EgQIQ_vecx8oqQIV1GEJ3vP8mSjUhu1vfrdA";

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
        CarDTO obj = service.getByRegistration(token, registration);
        System.out.println("model: " + obj.getCarModelId());
        System.out.println("client: " + obj.getClientId());
        assertAll(
               () -> assertNotNull(obj),
                () -> assertNotEquals(0, obj.getCarModelId()),
                () -> assertNotEquals(0, obj.getClientId()),
               () -> assertThat(obj, instanceOf(CarDTO.class))
       );
    }

    @Test
    @DisplayName("should return null if invalid registration")
    void getByRegistration1() {
        String registration = "05errrrf";
        CarDTO obj = service.getByRegistration(token, registration);
        assertNull(obj);
    }

    @Test
    void testGetObjectClass() {
    }

    @Test
    void testGetByRegistration() {
    }
}