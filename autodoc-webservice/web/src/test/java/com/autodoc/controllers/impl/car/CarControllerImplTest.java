package com.autodoc.controllers.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.dtos.car.CarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class CarControllerImplTest {


    String encoding = "application/json;charset=ISO-8859-1";
    int id = 3;
    String registration = "abc123";
    String feedback = "";
    List<CarDTO> cars = new ArrayList<>();
    //Client client = new Client("Jean", "Mako", "03938937837");
    //Manufacturer manufacturer = new Manufacturer("Mazda");
    String name = "Clio";
    //CarModel carModel = new CarModel(manufacturer, name, "joli", GearBox.MANUAL, "2.0", FuelType.DIESEL);
    CarDTO car;

    private CarControllerImpl carControllerImpl;
    private CarManager carManager;
    private MockMvc mockMvc;
    private GsonConverter converter;
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the car"),
            fieldWithPath("registration").description("registration of the car"),
            fieldWithPath("carModelId").description("Id of the carModel"),
            fieldWithPath("clientId").description("Id of the carModel")
    };

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        /*this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*/
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
    void getById() throws Exception {
        when(carManager.getById(anyInt())).thenReturn(car);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getById/{carId}", id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(id))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        assertEquals(response, carControllerImpl.getById(id));

    }

    @Test
    public void getAll() throws Exception {
        when(carManager.getAll()).thenReturn(cars);
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
    }


    @Test
    void getByRegistration() throws Exception {
        when(carManager.getByRegistration(anyString())).thenReturn(car);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getByRegistration")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        assertEquals(response, carControllerImpl.getCarByRegistration(registration));

    }

  /*  @Test
    void update() throws Exception {
        Car car = new Car();
        String feedback = "car updated";
        when(carManager.update(any(Car.class))).thenReturn(feedback);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put("/car/update")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(car))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}"));

        ResponseEntity response = ResponseEntity.ok(feedback);
        assertEquals(response, carControllerImpl.update(car));
    }*/

    @Test
    void addCar() throws Exception {
        feedback = "car added";
        CarDTO car = new CarDTO(registration, 2, 2);
        when(carManager.save(any(Object.class))).thenReturn(feedback);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post("/car/add")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(car))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
        ResponseEntity response = ResponseEntity.ok(feedback);
        assertEquals(response, carControllerImpl.add(car));
    }

    @Test
    void deleteCar() throws Exception {
        feedback = "car deleted";
        when(carManager.deleteById(any(Integer.class))).thenReturn(feedback);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/car/deleteById/{id}", id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(id))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(content().contentType(encoding))
                .andExpect(status().isOk())
                .andDo(document("{ClassName}/{methodName}"));
        ResponseEntity response = ResponseEntity.ok(feedback);
        assertEquals(response, carControllerImpl.deleteById(id));
    }
}