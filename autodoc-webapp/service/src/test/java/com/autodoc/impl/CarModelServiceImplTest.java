package com.autodoc.impl;

import com.autodoc.contract.CarModelService;
import com.autodoc.model.dtos.car.CarModelDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarModelServiceImplTest {

    private CarModelService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk1MzUzMSwiaWF0IjoxNTc0OTM1NTMxfQ.bIHrPZsU3QXPsMmupHb7hebHR5P3krJ32HlTfsbNNqQqzqO4miDuGY9qlLuiDu3hGQDzkja7T0xZlG051ncdJQ";
    private static final Logger LOGGER = Logger.getLogger(CarModelServiceImplTest.class);
    @BeforeEach
    void init() {
        service = new CarModelServiceImpl();
    }


    @Test
    void getById() {
        CarModelDTO model = (CarModelDTO) service.getById(token, 2);
        LOGGER.info("model: " + model);
        assertNotNull(service.getById(token, 2));
    }


    @Test
    void testGetObjectClass() {
    }

    @Test
    void testGetByRegistration() {
    }
}