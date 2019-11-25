package com.autodoc.impl;

import com.autodoc.contract.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeServiceImplTest {

    private CarService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDcxMjY5MCwiaWF0IjoxNTc0Njk0NjkwfQ.6fFaipPO2S46l0vORn-eo2uJQKLNLghgtNIJ6KE_cBWm8CloPbGrOUv7-DWrnsb5-gqpOOamugNBe7CJ12-e1w";

    @BeforeEach
    void init(){
        service = new CarServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByRegistration() {
        String registration = "05D154875";
        assertNotNull(service.getByRegistration(token, registration));
    }

    @Test
    void getByid() {
        assertNotNull(service.getById(token, 1));
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
    }
}