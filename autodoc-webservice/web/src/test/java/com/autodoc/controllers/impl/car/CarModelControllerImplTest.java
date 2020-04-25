package com.autodoc.controllers.impl.car;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class CarModelControllerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarModelControllerImplTest.class);
    private CarModelControllerImpl controller;
    private CarModelManager manager;
    private MockMvc mockMvc;
    String encoding = "application/json;charset=ISO-8859-1";
    int id = 3;
    List<CarModelDTO> objList = new ArrayList<>();
  //  Manufacturer manufacturer = new Manufacturer("PLOP");
    CarModelDTO carModel;
    String name = "quattro";
    String urlItem = "/carModels";
    private GsonConverter converter;
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the carModel"),
            fieldWithPath("manufacturerId").description("Id of the manufacturer"),
            fieldWithPath("name").description("Name of the carModel"),
            fieldWithPath("description").description("description of the carModel"),
            fieldWithPath("gearbox").description("Gearbox of the carModel"),
            fieldWithPath("engine").description("Engine of the carModel"),
            fieldWithPath("fuelType").description("FuelType of the carModel")
    };
    private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

/*this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*/
      carModel = CarModelDTO.builder().id(2).name(name).description("joli").gearbox("MANUAL").engine("2.0").fuelType("DIESEL").manufacturerId(3).build();
      converter = new GsonConverter();
      objList.add(carModel);
      manager = mock(CarModelManager.class);
      controller = new CarModelControllerImpl(manager);
      this.mockMvc = MockMvcBuilders
              .standaloneSetup(controller)
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
        // using standalone
      //  this.mockMvc = MockMvcBuilders.standaloneSetup(carModelControllerImpl).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
    }



    @Test
    public void getAll() throws Exception {
        when(manager.getAll()).thenReturn(objList);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem)
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));
        LOGGER.info("carModels: " + objList);
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(objList));
        assertEquals(response, controller.getAll());
    }


    @Test
    void getById() throws Exception {
        when(manager.getById(anyInt())).thenReturn(carModel);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem + "/{carModelId}", id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(id))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(carModel));
        assertEquals(response, controller.getById(id));

    }

    @Test
    public void getByName() throws Exception {

        when(manager.getByName(anyString())).thenReturn(carModel);

        this.mockMvc.perform(
                get(urlItem + "/name?name=" + name)
                        .header("Authorization", "Bearer token")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andExpect(jsonPath("$.name").value(name))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(carModel));
        assertEquals(response, controller.getByName(name));

    }


}
