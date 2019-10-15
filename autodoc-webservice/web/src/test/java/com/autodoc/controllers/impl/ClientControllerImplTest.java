package com.autodoc.controllers.impl;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.ClientController;
import com.autodoc.model.models.person.client.Client;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class ClientControllerImplTest {
    private Logger logger = Logger.getLogger(ClientControllerImplTest.class);

    private ClientController clientController;
    private ClientManager clientManager;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        clientManager = mock(ClientManager.class);
        clientController = new ClientControllerImpl(clientManager);
        logger.debug("context: " + webApplicationContext);
    }


    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/client/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        String response = new Gson().toJson(clients);
        when(clientManager.getAll()).thenReturn(clients);
        assertEquals(response, clientController.getAll());
    }


    @Test
    void addCar() {
        Client client = new Client();
        when(clientManager.save(any(Client.class))).thenReturn("car added");
        assertEquals("car added", clientController.addClient(client));
    }


    @Test
    void getClientByName() {
    }

    @Test
    void getClientById() {
    }

    @Test
    void addClient() {
        Client client = new Client();
        String response = "client added";
        when(clientManager.save(any(Client.class))).thenReturn(response);
        assertEquals(response, clientController.addClient(client));
    }

    @Test
    void updateClient() {
        Client client = new Client();
        String response = "client added";
        when(clientManager.update(any(Client.class))).thenReturn(response);
        assertEquals(response, clientController.updateClient(client));
    }

    @Test
    void deleteClient() {
        String response = "client removed";
        when(clientManager.delete(anyInt())).thenReturn(response);
        assertEquals(response, clientController.deleteClient(2));
    }
}