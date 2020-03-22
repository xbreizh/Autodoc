package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CountryServiceImplTest extends HelperTest {

    private GlobalService service;
    private static final Logger LOGGER = Logger.getLogger(CountryServiceImplTest.class);

    @BeforeEach
    void init() {

    }


    @Test
    @DisplayName("should return a car object if id valid")
    void getCountryById() {
        service = new CountryServiceImpl();
        int id = 1;
        LOGGER.info(service);
        CountryDTO country = (CountryDTO) service.getById(token, id);
        LOGGER.info("country: " + country);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return null if id invalid")
    void getCountryById1() {
        service = new CountryServiceImpl();
        int id = 441;
        LOGGER.info(service);
        CountryDTO country = (CountryDTO) service.getById(token, id);
        LOGGER.info("country: " + country);
        assertNull(service.getById(token, id));
    }


}