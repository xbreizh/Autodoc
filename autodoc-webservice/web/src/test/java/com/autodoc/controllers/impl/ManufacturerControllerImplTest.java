package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.impl.authentication.JwtConnect;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Manufacturer;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class ManufacturerControllerImplTest {
    private Logger logger = Logger.getLogger(ManufacturerControllerImplTest.class);
    private ManufacturerController manufacturerController;
    private ManufacturerManager manufacturerManager;
    private GsonConverter gsonConverter;
    private MockMvc mockMvc;
    private JwtConnect jwtConnect;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        manufacturerManager = mock(ManufacturerManager.class);
        manufacturerController = new ManufacturerControllerImpl(manufacturerManager, jwtConnect);
        gsonConverter = new GsonConverter();
        logger.debug("context: " + webApplicationContext);
    }


    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/manufacturer/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer());
        String response = new Gson().toJson(manufacturers);
        when(manufacturerManager.getAll()).thenReturn(manufacturers);
        assertEquals(response, manufacturerController.getAll());
    }

    @Test
    public void getByName() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/manufacturer/getByName"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        Manufacturer manufacturer = new Manufacturer();
        String response = new Gson().toJson(manufacturer);
        when(manufacturerManager.getByName(anyString())).thenReturn(manufacturer);
        assertEquals(response, manufacturerController.getByName("Lotus"));
    }
}