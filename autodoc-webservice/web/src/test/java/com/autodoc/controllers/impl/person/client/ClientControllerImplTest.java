package com.autodoc.controllers.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.person.client.ClientManagerImpl;
import com.autodoc.controllers.contract.person.client.ClientController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class ClientControllerImplTest {
    String urlItem = "/clients";
    private ClientManager clientManager;
    private MockMvc mockMvc;
    private GsonConverter converter;
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the car"),
            fieldWithPath("firstName").description("FirstName of the carModel"),
            fieldWithPath("lastName").description("LastName of the carModel"),
            fieldWithPath("phoneNumber1").description("PhoneNumber1 of the carModel")
    };
    private List<ClientDTO> clients = new ArrayList<>();
    //private static final Logger LOGGER = Logger.getLogger(ClientControllerImplTest.class);
    private ClientController clientController;
    private String name = "lmolo";
    private int id = 72;
    private ClientDTO clientDTO = new ClientDTO("Jean", name, "03938937837");

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        clientManager = mock(ClientManagerImpl.class);
        clientController = new ClientControllerImpl(clientManager);
        /*this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*/
        clients.add(clientDTO);
        converter = new GsonConverter();
        // using standalone
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
        // LOGGER.debug("context: " + webApplicationContext);
    }


    @Test
    public void getAll() throws Exception {

        when(clientManager.getAll()).thenReturn(clients);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem)
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));

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
    void getByName() throws Exception {
        when(clientManager.getByName(anyString())).thenReturn(clientDTO);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/name?name=" + name)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(clientDTO));
        assertEquals(response, clientController.getByName(name));
    }

    @Test
    void getById() throws Exception {
        when(clientManager.getById(anyInt())).thenReturn(clientDTO);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/" + id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(id))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(clientDTO));
        assertEquals(response, clientController.getById(id));
    }

  /*  @Test
    void add() throws Exception {
        Client client = new Client();
        when(clientManager.save(any(Client.class))).thenReturn("client added");
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/client/add")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(client))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok("client added");
        assertEquals(response, clientController.add(client));
    }*/

   /* @Test
    void update() throws Exception {
        ClientDTO client = new ClientDTO("Doe", "John", "12121212");
        when(clientManager.update(any(Client.class))).thenReturn(true);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/client/update")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(client))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok("client updated");
        assertEquals(response, clientController.update(client));
    }*/


}