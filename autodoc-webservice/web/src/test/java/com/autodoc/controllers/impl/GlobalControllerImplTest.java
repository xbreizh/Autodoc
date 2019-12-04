package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
class GlobalControllerImplTest {

    private IGenericManager<Car, CarDTO> manager;
    private MockMvc mockMvc;
    List<CarDTO> cars = new ArrayList<>();

    @Inject
    CarController carController;

    @Inject
    Filler filler;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
    }

    @Test
    void deleteById() throws Exception {
        int id = 2;
        assertEquals(200, carController.getById(id).getStatusCodeValue());
        assertEquals(204, carController.deleteById(id).getStatusCodeValue());
        assertEquals(404, carController.getById(id).getStatusCodeValue());
    }


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
}