package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeServiceImplTest {

    private EmployeeService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NTM0MTAyNiwiaWF0IjoxNTc1MzIzMDI2fQ.KqWMEon3T6K9WtftNvCKo9OLLOMZo2hbmoNWr2X6Bsezb_8xpoDDPVyXrdjrrYh0LXxif58J39S1BrL0B99VdQ";

    @BeforeEach
    void init() {
        service = new EmployeeServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByRegistration() {
        String login = "LOKII";
        assertNotNull(service.getByName(token, login));
    }

    @Test
    void getByid() {
        System.out.println("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        System.out.println("employee: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
    }
}