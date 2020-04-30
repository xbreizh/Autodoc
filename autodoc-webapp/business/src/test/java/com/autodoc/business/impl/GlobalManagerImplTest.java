package com.autodoc.business.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.contract.EnumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GlobalManagerImplTest {
    String feedback = "400 / For input string: \"that client already exist: ROGER MOORE\"";
    String clientFeedback = "400 / \"client already exist\"";
    ClientService service;
    EnumService enumService;
    private GlobalManagerImpl manager;

    @BeforeEach
    void init() {
        service = mock(ClientService.class);
        enumService = mock(EnumService.class);
        manager = new ClientManagerImpl(service);
    }

    @Test
    void getFeedbackDetails() {
        //  assertEquals("that client already exist: ROGER MOORE", manager.getFeedbackDetails(feedback));
        assertEquals("client already exist", manager.getFeedbackDetails(clientFeedback));
    }

    @Test
    void getFeedbackDetails1() {
        //  assertEquals("that client already exist: ROGER MOORE", manager.getFeedbackDetails(feedback));
        assertEquals("8", manager.getFeedbackDetails("201 / 8"));
    }

    @Test
    void getRequestCode() {

        assertEquals(400, manager.getRequestCode(feedback));
    }


    @Test
    void checkIfDateIsValid() throws Exception {
        String date = "12/12/2018 12:22";
        String date1 = "11-13-2018 14:22";
        String date2 = "12-12-2018 24:22";
        assertThrows(Exception.class, () -> manager.checkIfDateIsValid(date));
        assertThrows(Exception.class, () -> manager.checkIfDateIsValid(date1));
        assertThrows(Exception.class, () -> manager.checkIfDateIsValid(date2));

        assertDoesNotThrow(() -> manager.checkIfDateIsValid("02-02-2021 11:20"));
    }
}