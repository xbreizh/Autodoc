package com.autodoc.controllers.impl.car;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
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
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class CarControllerImplTest {
    private static final Logger LOGGER = Logger.getLogger(CarControllerImplTest.class);
    private final CarDTO carDTO = new CarDTO();
    private final FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the car"),
            fieldWithPath("registration").description("registration of the car"),
            fieldWithPath("carModelId").description("Id of the carModel"),
            fieldWithPath("clientId").description("Id of the carModel")
    };
    String encoding = "application/json;charset=ISO-8859-1";
    int id = 3;
    String registration = "05D121487";
    String feedback = "";
    List<CarDTO> carDTOS = new ArrayList<>();
    //Client client = new Client("Jean", "Mako", "03938937837");
    //Manufacturer manufacturer = new Manufacturer("Mazda");
    String urlItem = "/cars";
    String name = "Clio";
    //CarModel carModel = new CarModel(manufacturer, name, "joli", GearBox.MANUAL, "2.0", FuelType.DIESEL);
    CarDTO dto;
    private CarController carController;
    private CarManager carManager;
    private MockMvc mockMvc;
    private GsonConverter converter;
    private ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation)  {
        dto = CarDTO.builder().id(1).registration(registration).clientId(1).carModelId(3).color("yellow").mileage(457845).build();
        carDTOS.add(dto);
        converter = new GsonConverter();
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(carController)
                //.webAppContextSetup(webApplicationContext)
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

        // using standalone
       /* this.mockMvc = MockMvcBuilders.standaloneSetup(carController).apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .alwaysDo(document("{ClassName}/{methodName}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                  .alwaysDo(print())
                .build();*/
    }

    @AfterEach
    public void tearDown() {
        this.restDocumentation.afterTest();
    }


  /*  @Test
    @DisplayName("should return object if registration is valid")
    void test() throws Exception {
       // when(carManager.getByRegistration(anyString())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/test?registration=" + registration)
                        .header("Authorization", "Bearer test")
        )
                .andExpect(status().isOk());


    }*/



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
    void getByRegistration1() throws Exception {
        when(carManager.getByRegistration(anyString())).thenReturn(null);
       /* assertNotNull(carController.getByRegistration(registration));
        assertEquals(404, carController.getByRegistration(registration).getStatusCodeValue());*/
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
/*
    @Test
    @DisplayName("should return 200 if id is invalid")
    void getById() throws Exception {
        when(carManager.getById(anyInt())).thenReturn(dto);
        assertEquals(200, carController.getById(12).getStatusCodeValue());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/" + id)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(converter.convertObjectIntoGsonObject(dto))
                );


    }*/
    /*

    @Test
    @DisplayName("should return 404 if registration is invalid")
    void getByRegistration1() throws Exception {
        //when(carManager.getByRegistration(anyString())).thenReturn(null);
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.getByRegistration(anyString())).thenReturn(null);
        assertNotNull(carController.getByRegistration("dede"));
        assertEquals(404, carController.getByRegistration("momo").getStatusCodeValue());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getByRegistration")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andDo(document("{ClassName}/{methodName}"));
        //ResponseEntity response = ResponseEntity.notFound().build();
        response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        assertEquals(response, carControllerImpl.getCarByRegistration(registration));


    }


    @Test
    @DisplayName("should return object if id is valid")
    void getById() throws Exception {
        // String name = carDTO.getId();
        int id = 3;
        when(carManager.getById(id)).thenReturn(carDTO);
        assertEquals(200, carController.getById(id).getStatusCodeValue());
        this.mockMvc.perform(
                get(urlItem + "/" + id)
                        .header("Authorization", "Bearer token")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
        // .andExpect(jsonPath("$.name").value(name))
        // .andExpect(jsonPath("$.id").value(id))
    .andDo(document("{ClassName}/{methodName}",
                responseFields(descriptor)
        ));


    }

    @Test
    @DisplayName("should return 404 if id is invalid")
    void getById1() throws Exception {
        int id = 24;
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.getById(id)).thenReturn(null);
        assertEquals(404, carController.getById(id).getStatusCodeValue());
        LOGGER.info(carController.getById(id));
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/cars/" + id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
        // ResponseEntity response = ResponseEntity.notFound().build();
        // ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        // assertEquals(response, carControllerImpl.getCarByRegistration(registration));
    }

    @Test
    @DisplayName("should return 200 if element is deleted")
    void deleteById() throws Exception {
        int id = 24;
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.deleteById(anyInt())).thenReturn(true);
        assertEquals(204, carController.deleteById(id).getStatusCodeValue());
        LOGGER.info(carController.getById(id));
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/cars/" + id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
    }

    @Test
    @DisplayName("should return 200 if element is deleted")
    void deleteById1() throws Exception {
        int id = 24;
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.deleteById(anyInt())).thenReturn(false);
        assertEquals(404, carController.deleteById(id).getStatusCodeValue());
        LOGGER.info(carController.getById(id));
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/cars/" + id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
    }*/
}
