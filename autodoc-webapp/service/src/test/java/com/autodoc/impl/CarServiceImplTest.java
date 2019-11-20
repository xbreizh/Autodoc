package com.autodoc.impl;

import com.autodoc.contract.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceImplTest {

    private CarService service;
    private String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI4OTQxNCwiaWF0IjoxNTc0MjcxNDE0fQ.vKKtg7YvrRIGOrfvXyvomQdLSP1Z5rnP4OidTli-qCta98jZ27KfMqekE8aH2Dg6ck5OpmoFKCmCjGuSn6BO_w";

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
    void testGetObjectClass() {
    }

    @Test
    void testGetByRegistration() {
    }
}