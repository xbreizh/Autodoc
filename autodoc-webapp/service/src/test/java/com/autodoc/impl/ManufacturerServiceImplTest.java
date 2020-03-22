package com.autodoc.impl;

import com.autodoc.contract.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManufacturerServiceImplTest extends HelperTest {

    private ManufacturerService service;


    @BeforeEach
    void init() {
        this.token = getToken();
        service = new ManufacturerServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getById() {
        assertNotNull(service.getById(token, 2));


    }
}