package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.impl.authentication.JwtConnect;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class ManufacturerControllerImplTest {
    private final static String encoding = "application/json;charset=UTF-8";
    private Logger logger = Logger.getLogger(ManufacturerControllerImplTest.class);
    private ManufacturerController manufacturerController;
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;
    private MockMvc mockMvc;
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the manufacturer"),
            fieldWithPath("name").description("Name of the manufacturer")
    //        fieldWithPath("carModels").description("The number of car models")
    };
    List<Manufacturer> manufacturers = new ArrayList<>();
    Manufacturer m1 = new Manufacturer("BMW");
    Manufacturer m2 = new Manufacturer("AUDI");


    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        manufacturerManager = mock(ManufacturerManager.class);
        manufacturerController = new ManufacturerControllerImpl(manufacturerManager);
        converter = new GsonConverter();

        // using spring context
       /* this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*/


        // using standalone
        this.mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
        logger.debug("context: " + webApplicationContext);
        manufacturers.add(m1);
        manufacturers.add(m2);

    }


    @Test
    void getAll() throws Exception {
        when(manufacturerManager.getAll()).thenReturn(manufacturers);
        this.mockMvc.perform(
                get("/manufacturer/getAll")
                        .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(manufacturers.get(0).getName()))
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));
        System.out.println(manufacturers);
        assertEquals(manufacturers, manufacturerController.getAll());
    }

    @Test
    public void getByName() throws Exception {
        CarModel cm = new CarModel(m1, "quattro", "joli", GearBox.MANUAL, "2.0", FuelType.DIESEL);
        String name = m1.getName();
        int id = 3;
        m1.setId(id);
        when(manufacturerManager.getByName(anyString())).thenReturn(m1);

        this.mockMvc.perform(
                get("/manufacturer/getByName")
                        .header("Authorization", "Bearer token")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").value(id))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        System.out.println(m1);
    }
}