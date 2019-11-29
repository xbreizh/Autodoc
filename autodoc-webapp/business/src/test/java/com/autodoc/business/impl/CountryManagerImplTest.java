package com.autodoc.business.impl;

import com.autodoc.business.contract.CountryManager;
import com.autodoc.contract.CountryService;
import com.autodoc.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryManagerImplTest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NTA1OTQ1OSwiaWF0IjoxNTc1MDQxNDU5fQ.gJCEsF1qgI-USKDeUW952b-K8P-hoiJYHYbuBrbQyaXFm7z7lXu2iIq_7R5_BzKwKd1ARc05crxNvfio9zojeQ";
    private CountryManager manager;
    //@Inject
    private CountryService service;

    @BeforeEach
    void init() {
        service = new CountryServiceImpl();
        manager = new CountryManagerImpl(service);
    }


    @Test
    @DisplayName("should return an item when id is valid")
    void getById() {
        int id = 2;

        assertNotNull(manager.getById(token, id));
    }

    @Test
    @DisplayName("should return null when id is invalid")
    void getById1() {
        int id = 22;
        System.out.println(manager.getById(token, id));
        assertNull("feedback: " + manager.getById(token, id));
    }

    @Test
    @DisplayName("should return all countries")
    void getAll() {
        assertEquals(6, manager.getAll(token).size());
    }


}