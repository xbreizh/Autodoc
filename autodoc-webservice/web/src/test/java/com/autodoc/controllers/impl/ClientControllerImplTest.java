package com.autodoc.controllers.impl;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.ClientController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
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
    @Inject
    private GsonConverter converter;
    private ClientController clientController;
    private ClientManager clientManager;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        clientManager = mock(ClientManager.class);
        clientController = new ClientControllerImpl(clientManager);
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        logger.debug("context: " + webApplicationContext);
    }


    @Test
    public void getAll() throws Exception {
        List<Client> clients = new ArrayList<>();
        Client client = new Client("paul", "martin", "333225");
        clients.add(client);
        when(clientManager.getAll()).thenReturn(clients);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/client/getAll")
                        .header("Authorization", "Bearer test" ))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(clients));
        assertEquals(response, clientController.getAll());
    }


  /*  @Test
    void addClient() throws Exception {
        ResponseEntity response = ResponseEntity.ok("client added");
        when(clientManager.save(any(Client.class))).thenReturn("client added");
        assertEquals(response, clientController.getAll());
        String client = new Gson().toJson(new Client());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/client/add").content(client))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

    }
*/

    @Test
    void getClientByName() throws Exception {
        Client client = new Client();
        String name = "Gondry";
        client.setLastName(name);
        when(clientManager.getByName(anyString())).thenReturn(client);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/client/getByName")
                        .header("Authorization", "Bearer test" )
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(client));
        assertEquals(response, clientController.getClientByName(name));
    }

    @Test
    void getClientById() {
    }

    @Test
    void addClient() throws Exception {
        Client client = new Client();
        when(clientManager.save(client)).thenReturn("client added");
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/client/add")
                        .header("Authorization", "Bearer test" )
                        .content(converter.convertObjectIntoGsonObject(client))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok("client added");
        assertEquals(response, clientController.addClient(client));
    }

    @Test
    void updateClient() throws Exception {
        Client client = new Client();
        when(clientManager.update(client)).thenReturn("client updated");
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/client/update")
                        .header("Authorization", "Bearer test" )
                        .content(converter.convertObjectIntoGsonObject(client))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok("client updated");
        assertEquals(response, clientController.updateClient(client));
    }


}