package com.autodoc.controllers.impl;

import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.model.models.car.Manufacturer;
import com.google.gson.Gson;
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

    private ManufacturerController manufacturerController;
    private ManufacturerManager manufacturerManager;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        manufacturerManager = mock(ManufacturerManager.class);
        manufacturerController = new ManufacturerControllerImpl(manufacturerManager);
        System.out.println("context: " + webApplicationContext);
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
}