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

class CarServiceImplTest extends HelperTest {

    private CarService service;

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
    @DisplayName("should return 200 if all valid")
    void update() {
        CarDTO car = (CarDTO) service.getAll(token).get(0);
        car.setClientId(2);
        assertEquals("200", service.update(token, car));
    }

    @Test
    @DisplayName("should return 404 if invalid clientId")
    void update1() {
        int clientId=23;
        CarDTO car = (CarDTO) service.getAll(token).get(0);
        car.setClientId(clientId);
        assertEquals("404 / invalid clientId: "+clientId, service.update(token, car));
    }

    @Test
    void testGetObjectClass() {
    }

    @Test
    void testGetByRegistration() {
    }
}