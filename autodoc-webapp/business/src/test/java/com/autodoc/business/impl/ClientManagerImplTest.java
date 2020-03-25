package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.contract.EnumService;
import com.autodoc.impl.ClientServiceImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.client.ClientForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientManagerImplTest {

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDM2MDgxNywiaWF0IjoxNTc0MzQyODE3fQ.GbMKAFeTXG3ThuZGE0W5hQqYB3x5rB9jTrFwfU9D62oZoE_jPaKiR7p3CF2KSBpJFUrtxORV8XKHUMPCktAWXw";
    private ClientManager clientManager;
    //@Inject
    private ClientService service;
    private EnumService enumService;

    @BeforeEach
    void init() {
        service = mock(ClientServiceImpl.class);
        clientManager = new ClientManagerImpl(service);
    }

    @Test
    void getById() throws Exception {
        when(service.getById(anyString(), anyInt())).thenReturn(new ClientDTO());
        int id = 1;
        assertNotNull(clientManager.getById(token, id));
    }


    @Test
    void add() throws Exception {
        when(service.create(anyString(), any(ClientDTO.class))).thenReturn("400 / For input string: \"that client already exist: ROGER MOORE\"");
        ClientForm form = new ClientForm();
        assertEquals("that client already exist: ROGER MOORE", clientManager.add(token, form));
    }
}