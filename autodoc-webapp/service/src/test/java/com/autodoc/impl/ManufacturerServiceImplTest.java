package com.autodoc.impl;

import com.autodoc.contract.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManufacturerServiceImplTest {

    private ManufacturerService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk1MzUzMSwiaWF0IjoxNTc0OTM1NTMxfQ.bIHrPZsU3QXPsMmupHb7hebHR5P3krJ32HlTfsbNNqQqzqO4miDuGY9qlLuiDu3hGQDzkja7T0xZlG051ncdJQ";

    @BeforeEach
    void init() {
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