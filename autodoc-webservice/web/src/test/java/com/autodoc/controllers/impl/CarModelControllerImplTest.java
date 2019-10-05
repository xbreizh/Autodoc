package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.model.models.car.CarModel;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class CarModelControllerImplTest {


    private CarModelControllerImpl carModelControllerImpl;
    private CarModelManager carModelManager;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        carModelManager = mock(CarModelManager.class);
        carModelControllerImpl = new CarModelControllerImpl(carModelManager);
    }


    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/carModel/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        List<CarModel> carModels = new ArrayList<>();
        carModels.add(new CarModel());
        String response = new Gson().toJson(carModels);
        when(carModelManager.getAll()).thenReturn(carModels);
        assertEquals(response, carModelControllerImpl.getAll());
    }

    @Test
    public void getById() throws Exception {
        CarModel carModel = new CarModel();
        carModel.setName("bob");
        when(carModelManager.getById(anyInt())).thenReturn(carModel);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/carModel/getById/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}", pathParameters(
                        parameterWithName("id").description("The id of the input to delete"))));
        String response = new Gson().toJson(carModel);
        assertEquals(response, carModelControllerImpl.getById(10));
    }


}