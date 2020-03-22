package com.autodoc.impl;

import com.autodoc.contract.CarModelService;
import com.autodoc.model.dtos.car.CarModelDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarModelServiceImplTest extends HelperTest {

    private CarModelService service;
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