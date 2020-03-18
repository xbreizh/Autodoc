package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.contract.EnumService;
import com.autodoc.impl.ClientServiceImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        clientManager = new ClientManagerImpl(service, enumService);
    }

    @Test
    void getById() throws Exception {

        int id = 1;
        assertNotNull(clientManager.getById(token, id));
    }


    @Test
    void add() throws Exception {
        when(service.create(anyString(), any(ClientDTO.class))).thenReturn("11");
        ClientDTO dto = new ClientDTO();
        assertEquals("11", service.create("test", dto));
    }
}