package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientServiceImplTest extends HelperTest {

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImplTest.class);
    private ClientService service;

    private ClientDTO dto;


    @BeforeEach
    void init() {

        service = new ClientServiceImpl();
        dto = new ClientDTO();
        dto.setLastName("john");
        dto.setFirstName("ssss");
        dto.setPhoneNumber("32323232323");

    }


    @Test
    void getObjectClass() {
    }


    @Test
    @DisplayName("should return client id when insertion ok")
    void create() {
        assertDoesNotThrow(() -> Integer.parseInt(service.create(token, dto)));


    }

    @Test
    @DisplayName("should return exception if name already exist")
    void create1() {
        // trying to insert twice the same value
        service.create(token, dto);
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(service.create(token, dto)));


    }


}