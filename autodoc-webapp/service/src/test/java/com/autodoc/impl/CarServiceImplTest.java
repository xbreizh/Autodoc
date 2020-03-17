package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceImplTest {

    private CarService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjUxMjU5NSwiaWF0IjoxNTc2NDk0NTk1fQ.vBhwMkX1UII2cxkq4oaqTJOiBb4tCip0tq35NR8Eb-ECPwHG_Oz75YJq9iybf3U3J6MccTP07gLKu9x67MEacg";
    private static final Logger LOGGER = Logger.getLogger(CarServiceImplTest.class);
    private CarDTO dto;

    @BeforeEach
    void init() {
        service = new CarServiceImpl();
        dto = new CarDTO();
        dto.setClientId(2);
        dto.setCarModelId(1);
        dto.setRegistration("abc1234");
    }


    @Test
    void getObjectClass() {
    }

    @Test
    @DisplayName("should retuen carDto if registration is valid")
    void getByRegistration() {
        String registration = "05D154875";
        CarDTO obj = service.getByRegistration(token, registration);
        LOGGER.info("model: " + obj.getCarModelId());
        LOGGER.info("client: " + obj.getClientId());
        assertAll(
                () -> assertNotNull(obj),
                () -> assertNotEquals(0, obj.getCarModelId()),
                () -> assertNotEquals(0, obj.getClientId()),
                () -> assertThat(obj, instanceOf(CarDTO.class))
        );
    }

    @Test
    @DisplayName("should return 201 when insertion ok")
    void create() {
        System.out.println("feedback: " + service.create(token, dto));
        assertEquals(201, service.create(token, dto));


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