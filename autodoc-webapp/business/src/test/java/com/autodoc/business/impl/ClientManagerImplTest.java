package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientManagerImplTest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDM2MDgxNywiaWF0IjoxNTc0MzQyODE3fQ.GbMKAFeTXG3ThuZGE0W5hQqYB3x5rB9jTrFwfU9D62oZoE_jPaKiR7p3CF2KSBpJFUrtxORV8XKHUMPCktAWXw";
    private ClientManager clientManager;
    //@Inject
    private ClientService service;

    @BeforeEach
    void init() {
        service = new ClientServiceImpl();
        clientManager = new ClientManagerImpl(service);
    }

    @Test
    void getById() {

        int id = 1;
        assertNotNull(clientManager.getById(token, id));
    }
}