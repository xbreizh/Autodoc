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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class CarControllerImplTest {
    private static final Logger LOGGER = Logger.getLogger(CarControllerImplTest.class);
    private final FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the car"),
            fieldWithPath("registration").description("registration of the car"),
            fieldWithPath("carModelId").description("Id of the carModel"),
            fieldWithPath("clientId").description("Id of the carModel")
    };
    String urlItem = "/cars";
    int id = 3;
    String registration = "05D121487";
    List<CarDTO> carDTOs = new ArrayList<>();
    CarDTO dto;
    private CarController carController;
    private CarManager carManager;
    private MockMvc mockMvc;
    private MockMvc mockMvcException;
    private GsonConverter converter = new GsonConverter();
    private ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        dto = CarDTO.builder().id(1).registration(registration).clientId(1).carModelId(3).color("yellow").mileage(457845).build();
        carDTOs.add(dto);
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        this.mockMvcException = MockMvcBuilders.standaloneSetup(carController)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
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
                .standaloneSetup(carController)
                //.webAppContextSetup(webApplicationContext)  // to be used for integration testing
                .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
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
        this.restDocumentation.afterTest();
    }

    @Test
    @DisplayName("should return list of items")
    void getAll() throws Exception {
        when(carManager.getAll()).thenReturn(carDTOs);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(converter.convertObjectIntoGsonObject(carDTOs))
                );
    }


    @Test
    @DisplayName("should add object if valid")
    void create() throws Exception {
        dto.setId(0);
        when(carManager.getByRegistration(anyString())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post(urlItem)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated())
        ;
    }

    @Test
    @DisplayName("should update object if valid")
    void update() throws Exception {
        when(carManager.update(any())).thenReturn(true);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(urlItem)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("should delete object if valid")
    void delete() throws Exception {
        when(carManager.deleteById(anyInt())).thenReturn(true);

        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete(urlItem + "/" + id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNoContent())
        ;
    }


    @Test
    @DisplayName("should return object if registration is valid")
    void getByRegistration() throws Exception {
        when(carManager.getByRegistration(anyString())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/registration?registration=" + registration)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(converter.convertObjectIntoGsonObject(dto))
                );


    }

    @Test
    @DisplayName("should return 404 if registration is invalid")
    void getByRegistrationError() throws Exception {
        when(carManager.getByRegistration(anyString())).thenReturn(null);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/registration?registration=" + "21212")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andDo(document("{ClassName}/{methodName}"));


    }

    @Test
    @DisplayName("should return 200 if id is valid")
    void getById() throws Exception {
        when(carManager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/" + id)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(converter.convertObjectIntoGsonObject(dto))
                );
    }

    @Test
    @DisplayName("should return 404 if id is invalid")
    void getByIdError() throws Exception {
        when(carManager.getById(anyInt())).thenReturn(null);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/" + id)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
        ;
    }


    @Test
    @DisplayName("should update car client")
    void updateCarClient() throws Exception {
        when(carManager.updateClient(anyInt(), anyInt())).thenReturn(dto);
        int carId = 3;
        int clientId = 5;
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(urlItem + "/updateClient/" + carId + "/" + clientId)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(converter.convertObjectIntoGsonObject(dto))
                );
    }

    @Test
    @DisplayName("should update car client")
    void updateCarClientError() throws Exception {
        InvalidDtoException exception = new InvalidDtoException("client not found");
        when(carManager.updateClient(anyInt(), anyInt())).thenThrow(exception);
        int carId = 3;
        int clientId = 5;
        this.mockMvcException.perform(
                RestDocumentationRequestBuilders
                        .put(urlItem + "/updateClient/" + carId + "/" + clientId)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @DisplayName("should return 405")
    void getByName() throws Exception {
        assertThrows(Exception.class, () -> carController.getByName("name"));
        this.mockMvcException.perform(
                RestDocumentationRequestBuilders
                        .put(urlItem + "/name")
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("should return 405")
    void getByClient() throws Exception {
        int clientId = 5;
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(urlItem + "/byClient/")
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isMethodNotAllowed()
                );
    }

}
