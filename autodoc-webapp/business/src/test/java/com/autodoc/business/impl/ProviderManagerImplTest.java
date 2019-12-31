package com.autodoc.business.impl;

import com.autodoc.business.contract.ProviderManager;
import com.autodoc.contract.ProviderService;
import com.autodoc.impl.ProviderServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProviderManagerImplTest {
    // private static final String baseUrl = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjUzNDM5MCwiaWF0IjoxNTc2NTE2MzkwfQ.r64Ed4Df6I8ZbL8LK9p4ZLDye_kH5UcwhhbZaS4HQQXbDLzE1Z6dTo7Bn51qHyzpZJE1MpDl-wJSTEPq_ytBUA";
    // String url = "http://localhost:8087/autodoc/employees";
    private ProviderManager manager;
    //@Inject
    private ProviderService service;

    @BeforeEach
    void init() {
        service = new ProviderServiceImpl();
        manager = new ProviderManagerImpl(service);
    }


    @Test
    @DisplayName("should return a valid employee")
    public void getById()
            throws IOException {

        int id = 1;

        assertNotNull(manager.getById(token, id));

    }

    @Test
    @DisplayName("should return a valid employee")
    public void getById1()
            throws IOException {

        int id = 3333331;
        LOGGER.info("dede: " + manager.getById(token, id));
        assertNull(manager.getById(token, id));

    }


    @Test
    void getAll() {

        assertNotNull(manager.getAll(token));
    }

}