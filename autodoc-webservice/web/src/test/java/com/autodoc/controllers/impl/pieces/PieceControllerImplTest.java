package com.autodoc.controllers.impl.pieces;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.controllers.contract.pieces.PieceController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.exceptions.CustomRestExceptionHandler;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class PieceControllerImplTest {
    FieldDescriptor id = fieldWithPath("id").description("Id of the piece");
    FieldDescriptor name = fieldWithPath("name").description("Name of the piece");
    FieldDescriptor brand = fieldWithPath("brand").description("Brand of the piece");
    FieldDescriptor pieceTypeId = fieldWithPath("pieceTypeId").description("PieceTypeId of the piece");
    FieldDescriptor quantity = fieldWithPath("quantity").description("Quantity of the piece");
    FieldDescriptor buyingPrice = fieldWithPath("buyingPrice").description("BuyingPrice of the piece");
    FieldDescriptor sellPrice = fieldWithPath("sellPrice").description("SellPrice of the piece");

    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            id,  name, brand, pieceTypeId, quantity, buyingPrice, sellPrice
    };
    private final FieldDescriptor[] descriptorInsert = new FieldDescriptor[]{
            id,  name, brand, pieceTypeId, quantity, buyingPrice, sellPrice
    };
    private final FieldDescriptor[] descriptorUpdate = new FieldDescriptor[]{
            id,  name.optional(), brand.optional(), pieceTypeId.optional(), quantity.optional(), buyingPrice.optional(), sellPrice.optional()
    };

    private static final Logger LOGGER = Logger.getLogger(PieceControllerImplTest.class);
    private static final String ENCODING = "application/json;charset=ISO-8859-1";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String URL_ITEM = "/pieces";
    private static final int ID = 2;
    private static final String NAME = "MASTEROIL 2.4";
    private static final String BRAND = "CASTROL";
    private static final int PIECETYPEID = 7;
    private static final int QUANTITY = 23;
    private static final double BUYINGPRICE = 12.25;
    private static final double SELLPRICE = 18.20;
    private static final GsonConverter CONVERTER = new GsonConverter();
    private static final ManualRestDocumentation REST_DOCUMENTATION = new ManualRestDocumentation();
    List<PieceDTO> objList = new ArrayList<>();
    PieceDTO dto;
    private PieceController controller;
    private PieceManager manager;
    private MockMvc mockMvc;
    private MockMvc mockMvcException;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        dto = PieceDTO.builder().id(2).name(NAME).brand(BRAND).pieceTypeId(PIECETYPEID).quantity(QUANTITY).buyingPrice(BUYINGPRICE).sellPrice(SELLPRICE).build();
        objList.add(dto);
        manager = mock(PieceManager.class);
        controller = new PieceControllerImpl(manager);
        this.mockMvcException = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .alwaysDo(JacksonResultHandlers.prepareJackson(MAPPER))
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8087)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .alwaysDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                //.webAppContextSetup(webApplicationContext)  // to be used for integration testing
                .alwaysDo(JacksonResultHandlers.prepareJackson(MAPPER))
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8087)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .alwaysDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
        // using standalone
        //  this.mockMvc = MockMvcBuilders.standaloneSetup(pieceControllerImpl).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
    }

    @AfterEach
    public void tearDown() {
        this.REST_DOCUMENTATION.afterTest();
    }


    @Test
    public void getAll() throws Exception {
        when(manager.getAll()).thenReturn(objList);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM)
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ENCODING))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));
        LOGGER.info("pieces: " + objList);
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(objList));
        assertEquals(response, controller.getAll());
    }


    @Test
    void getById() throws Exception {
        when(manager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/{pieceId}", ID)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(ID))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(ENCODING))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(dto));
        assertEquals(response, controller.getById(ID));

    }

    @Test
    @DisplayName("should return 404 if id is invalid")
    void getByIdError() throws Exception {
        when(manager.getById(anyInt())).thenReturn(null);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/" + ID)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void getByName() throws Exception {

        when(manager.getByName(anyString())).thenReturn(dto);

        this.mockMvc.perform(
                get(URL_ITEM + "/name?name=" + NAME)
                        .header("Authorization", "Bearer token")
                        .content(CONVERTER.convertObjectIntoGsonObject(NAME))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(ENCODING))
                .andExpect(jsonPath("$.name").value(NAME))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(dto));
        assertEquals(response, controller.getByName(NAME));

    }

    @Test
    public void getByNameError() throws Exception {

        when(manager.getByName(anyString())).thenReturn(null);

        this.mockMvc.perform(
                get(URL_ITEM + "/name?name=" + NAME)
                        .header("Authorization", "Bearer token")
                        .content(CONVERTER.convertObjectIntoGsonObject(NAME))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should add object if valid")
    void create() throws Exception {
        dto.setId(0);
        when(manager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post(URL_ITEM)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated())
                .andDo(document("{ClassName}/{methodName}",
                        requestFields(descriptorInsert)
                ));

    }

    @Test
    @DisplayName("should update object if valid")
    void update() throws Exception {
        when(manager.update(any())).thenReturn(true);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk()).andDo(document("{ClassName}/{methodName}",
                requestFields(descriptorUpdate)
        ));
    }

    @Test
    @DisplayName("should delete object if valid")
    void delete() throws Exception {
        when(manager.deleteById(anyInt())).thenReturn(true);

        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete(URL_ITEM + "/" + ID)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNoContent())
        ;
    }


}