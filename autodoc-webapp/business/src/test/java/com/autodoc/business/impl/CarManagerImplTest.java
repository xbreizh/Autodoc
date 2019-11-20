package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.contract.CarService;
import com.autodoc.impl.CarServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarManagerImplTest {

    private static final String BASE_URL = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI3MTMyNiwiaWF0IjoxNTc0MjUzMzI2fQ.jgs9QiIhXKF2Li6VY6PwaVYb6ubdtyYEw-MbJLzO3zIszR5mkArZeVznXSkU1v5lk7yKvxMVeJgoJ7BGL0U4aA";
    // String url = "http://localhost:8087/autodoc/employees";
    private CarManager carManager;
    private CarService carService;

    @BeforeEach
    void init() {
        carService = new CarServiceImpl();
        carManager = new CarManagerImpl(carService);
    }

    @Test
    void getByRegistration() {
        String registration = "05D154875";

        assertNotNull(carManager.getByRegistration(token, registration));
    }


    @Test
    void getById() {
        int id = 2;

        assertNotNull(carManager.getById(token, id));
    }


}