package com.autodoc.business.impl;

import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.CarModelService;
import com.autodoc.impl.CarModelServiceImpl;
import com.autodoc.impl.ManufacturerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarModelManagerImplTest {
    /*
        private static final String BASE_URL = "http://localhost:8087/autodoc/";
        private final ObjectMapper objectMapper = new ObjectMapper();*/
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk1MzUzMSwiaWF0IjoxNTc0OTM1NTMxfQ.bIHrPZsU3QXPsMmupHb7hebHR5P3krJ32HlTfsbNNqQqzqO4miDuGY9qlLuiDu3hGQDzkja7T0xZlG051ncdJQ";

    private CarModelManager manager;
    @Inject
    private ManufacturerManager manufacturerManager;
    //@Inject
    private CarModelService service;

    @BeforeEach
    void init() {
        manufacturerManager = new ManufacturerManagerImpl(new ManufacturerServiceImpl());
        service = new CarModelServiceImpl();
        manager = new CarModelManagerImpl(service, manufacturerManager);
    }


    @Test
    void getById() {
        int id = 2;

        assertNotNull(manager.getById(token, id));
    }


}