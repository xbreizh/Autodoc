package com.autodoc.controllers.impl.car;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.exceptions.CustomRestExceptionHandler;
import com.autodoc.model.dtos.car.CarDTO;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
//@Transactional
class CarControllerImplTest {
    private static final Logger LOGGER = Logger.getLogger(CarControllerImplTest.class);
    private static final String ENCODING = "application/json;charset=ISO-8859-1";
    FieldDescriptor id = fieldWithPath("id").description("Id of the car");
    FieldDescriptor registration = fieldWithPath("registration").description("Id of the car");
    FieldDescriptor carModelId = fieldWithPath("carModelId").description("Id of the carModel");
    FieldDescriptor clientId = fieldWithPath("clientId").description("Id of the client");
    FieldDescriptor mileage = fieldWithPath("mileage").description("number of kilometers");
    FieldDescriptor color = fieldWithPath("color").description("color of the car");

    private final FieldDescriptor[] descriptor = new FieldDescriptor[]{
           id,registration, carModelId, clientId, mileage, color
    };
    private final FieldDescriptor[] descriptorInsert = new FieldDescriptor[]{
            id,registration, carModelId, clientId, mileage.optional(), color.optional()
    };
    private final FieldDescriptor[] descriptorUpdate = new FieldDescriptor[]{
            id.description("Id or registration required").optional(),
            registration.description("Id or registration required").optional(),
            carModelId.optional(),
            clientId.optional(),
            mileage.optional(),
            color.optional()
    };
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String URL_ITEM = "/cars";
    private static final int ID = 2;
    private static final String REGISTRATION = "ABC12345";
    private static final GsonConverter CONVERTER = new GsonConverter();
    private static final ManualRestDocumentation REST_DOCUMENTATION = new ManualRestDocumentation();

    private CarController controller;
    private CarManager manager;
    private MockMvc mockMvc;
    private MockMvc mockMvcException;

    List<CarDTO> objList = new ArrayList<>();
    CarDTO dto;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        dto = CarDTO.builder().id(1).registration("05D121487").clientId(1).carModelId(3).color("yellow").mileage(457845).build();
        objList.add(dto);
        manager = mock(CarManager.class);
        controller = new CarControllerImpl(manager);
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

    }

    @AfterEach
    public void tearDown() {
        this.REST_DOCUMENTATION.afterTest();
    }

    @Test
    @DisplayName("should return list of items")
    void getAll() throws Exception {
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
        LOGGER.info("carModels: " + objList);
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(objList));
        assertEquals(response, controller.getAll());
    }


    @Test
    @DisplayName("should return 405")
    void getByName() throws Exception {
        assertThrows(Exception.class, () -> controller.getByName("name"));
        this.mockMvcException.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM + "/name")
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("should return 405")
    void getByClient() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM + "/byClient/")
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isMethodNotAllowed()
                );
    }


    @Test
    @DisplayName("should return object if registration is valid")
    void getByRegistration() throws Exception {
        when(manager.getByRegistration(anyString())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/registration?registration=" + REGISTRATION)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(CONVERTER.convertObjectIntoGsonObject(dto)))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ;


    }

    @Test
    @DisplayName("should return 404 if registration is invalid")
    void getByRegistrationError() throws Exception {
        when(manager.getByRegistration(anyString())).thenReturn(null);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/registration?registration=" + REGISTRATION)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andDo(document("{ClassName}/{methodName}"));


    }

    @Test
    @DisplayName("should return 200 if id is valid")
    void getById() throws Exception {
        when(manager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/" + ID)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(CONVERTER.convertObjectIntoGsonObject(dto)))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
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
    @DisplayName("should update car client")
    void updateCarClient() throws Exception {
        when(manager.updateClient(anyInt(), anyInt())).thenReturn(dto);
        int carId = 3;
        int clientId = 5;
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM + "/updateClient/" + carId + "/" + clientId)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(CONVERTER.convertObjectIntoGsonObject(dto))
                );
    }

    @Test
    @DisplayName("should update car client")
    void updateCarClientError() throws Exception {
        InvalidDtoException exception = new InvalidDtoException("client not found");
        when(manager.updateClient(anyInt(), anyInt())).thenThrow(exception);
        int carId = 3;
        int clientId = 5;
        this.mockMvcException.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM + "/updateClient/" + carId + "/" + clientId)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isBadRequest())
        ;
    }




    @Test
    @DisplayName("should add object if valid")
    void create() throws Exception {
        dto.setId(0);
        when(manager.getByRegistration(anyString())).thenReturn(dto);
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
