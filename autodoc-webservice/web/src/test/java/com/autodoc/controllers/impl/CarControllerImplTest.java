package com.autodoc.controllers.impl;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
class CarControllerImplTest {


    private CarControllerImpl carControllerImpl;
    private CarManager carManager;
    private MockMvc mockMvc;
    @Inject
    private GsonConverter converter;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();


        carManager = mock(CarManager.class);
        carControllerImpl = new CarControllerImpl(carManager);
    }


    @Test
    public void getAll() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        when(carManager.getAll()).thenReturn(cars);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getAll")
                        .header("Authorization", "Bearer test" ))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(cars));
        assertEquals(response, carControllerImpl.getAll());
    }


    @Test
    void getByRegistration() throws Exception {
        String registration = "abc123";
        Car car = new Car();
        car.setRegistration(registration);
        when(carManager.getByRegistration(anyString())).thenReturn(car);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getByRegistration")
                        .header("Authorization", "Bearer test" )
                        .content(converter.convertObjectIntoGsonObject(registration))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        assertEquals(response, carControllerImpl.getCarByRegistration(registration));

    }

    @Test
    void update() throws Exception {
        Car car = new Car();
        car.setRegistration("newBetter");
        String feedback = "car updated";
        when(carManager.update(car)).thenReturn(feedback);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/car/update")
                        .header("Authorization", "Bearer test" )
                        .content(converter.convertObjectIntoGsonObject(car))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok(feedback);
        assertEquals(response, carControllerImpl.updateCar(car));
    }

    @Test
    void addCar() throws Exception {
        Car car = new Car();
        car.setRegistration("deded");
        when(carManager.save(car)).thenReturn("car added");
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/car/add")
                        .header("Authorization", "Bearer test" )
        .content(converter.convertObjectIntoGsonObject(car))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok("car added");
        assertEquals(response, carControllerImpl.addCar(car));
    }
}