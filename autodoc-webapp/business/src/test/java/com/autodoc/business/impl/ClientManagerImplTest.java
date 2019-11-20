package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

class ClientManagerImplTest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI3MTMyNiwiaWF0IjoxNTc0MjUzMzI2fQ.jgs9QiIhXKF2Li6VY6PwaVYb6ubdtyYEw-MbJLzO3zIszR5mkArZeVznXSkU1v5lk7yKvxMVeJgoJ7BGL0U4aA";
    private ClientManager clientManager;
    @Inject
    private ClientService service;

    @BeforeEach
    void init() {
        clientManager = new ClientManagerImplImpl(service);
    }

    @Test
    void getById() {

        int id = 2;
        assertNotNull(clientManager.getById(token, id));
    }
}