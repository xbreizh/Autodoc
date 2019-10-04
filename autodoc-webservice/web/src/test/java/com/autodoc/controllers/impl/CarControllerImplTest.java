package com.autodoc.controllers.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.google.gson.Gson;
import org.junit.jupiter.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class CarControllerImplTest {


    private CarControllerImpl carControllerImpl;
    private CarManager carManager;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        carManager = mock(CarManager.class);
        carControllerImpl = new CarControllerImpl(carManager);
        System.out.println("context: "+webApplicationContext);
    }


    @Test
    public void getPersonByIdShouldReturnOk() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
    }





    @Test
    void getAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        String response = new Gson().toJson(cars);
        when(carManager.getAll()).thenReturn(cars);
        assertEquals(response, carControllerImpl.getAll());
    }

    @Test
    void get() {
    }

    @Test
    void update() {
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setName("mazda");
        car.setRegistration("abs");
        car.setCarModel(carModel);

        String response = carControllerImpl.convertObjectIntoGsonObject(car);
        System.out.println("car: " + response);
    }

    @Test
    void addCar() {
        Car car = new Car();
        when(carManager.save(any(Car.class))).thenReturn(true);
        // assertEquals("car added", carControllerImpl.addCar(car));
    }
}