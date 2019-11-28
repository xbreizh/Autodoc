package com.autodoc.business.impl;

import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.ManufacturerService;
import com.autodoc.impl.ManufacturerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManufacturerManagerImplTest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk2ODA1MCwiaWF0IjoxNTc0OTUwMDUwfQ.BfKxkfEVLatJRdOfVph7XqJHnCBWPGFKyaq2exJNUYFMr9lU0-EgQIQ_vecx8oqQIV1GEJ3vP8mSjUhu1vfrdA";
    private ManufacturerManager manager;
    //@Inject
    private ManufacturerService service;

    @BeforeEach
    void init() {
        service = new ManufacturerServiceImpl();
        manager = new ManufacturerManagerImpl(service);
    }


    @Test
    void getById() {
        int id = 2;

        assertNotNull(manager.getById(token, id));
    }


}