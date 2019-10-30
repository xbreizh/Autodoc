package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class GlobalControllerImplTest {

    private IGenericManager<Car, CarDTO> manager;
    private MockMvc mockMvc;
    List<CarDTO> cars = new ArrayList<>();
/*
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        *//*this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*//*
        //car.setRegistration(registration);
        //client.setPhoneNumber2("123456");
        car = new CarDTO("ABC123", 1, 2);
        cars.add(car);
        converter = new GsonConverter();
        carManager = mock(CarManager.class);
        carControllerImpl = new CarControllerImpl(carManager);
        // using standalone
        this.mockMvc = MockMvcBuilders.standaloneSetup(carControllerImpl).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
    }

    @Test
    void getAll() {
        when(manager.getAll()).thenReturn(cars);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getAll")
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));

        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(cars));
        assertEquals(response, carControllerImpl.getAll());
    }*/

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void deleteById() {
    }
}