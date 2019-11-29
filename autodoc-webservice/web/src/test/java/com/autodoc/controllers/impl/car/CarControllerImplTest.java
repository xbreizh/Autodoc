package com.autodoc.controllers.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.dtos.car.CarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class CarControllerImplTest {

    private CarDTO carDTO = new CarDTO();
    String encoding = "application/json;charset=ISO-8859-1";
    int id = 3;
    String registration = "abc123";
    String feedback = "";
    List<CarDTO> carDTOS = new ArrayList<>();
    //Client client = new Client("Jean", "Mako", "03938937837");
    //Manufacturer manufacturer = new Manufacturer("Mazda");
    String urlItem = "/cars";
    String name = "Clio";
    //CarModel carModel = new CarModel(manufacturer, name, "joli", GearBox.MANUAL, "2.0", FuelType.DIESEL);
    CarDTO dto;

    private CarController carController;
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
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();
        //car.setRegistration(registration);
        //client.setPhoneNumber2("123456");
        dto = new CarDTO("ABC123", 1, 2);
        carDTOS.add(dto);
        converter = new GsonConverter();
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        // using standalone
        this.mockMvc = MockMvcBuilders.standaloneSetup(carController).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
    }


 /*   @Test
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
    @DisplayName("should return 200 and carDto if registration is valid")
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

    }*/

    @Test
    @DisplayName("should return object if registration is valid")
    void getByRegistration() throws Exception {
        //when(carManager.getByRegistration(anyString())).thenReturn(null);
        String registration = "D12s447F";
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.getByRegistration(registration)).thenReturn(new CarDTO());
        assertNotNull(carController.getByRegistration(registration));
        assertEquals(200, carController.getByRegistration("momo").getStatusCodeValue());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem+"/registration?registration="+registration)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(document("{ClassName}/{methodName}"));
        //ResponseEntity response = ResponseEntity.notFound().build();
        /* response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
         assertEquals(response, carControllerImpl.getCarByRegistration(registration));*/

    }

    @Test
    @DisplayName("should return 404 if registration is invalid")
    void getByRegistration1() throws Exception {
        //when(carManager.getByRegistration(anyString())).thenReturn(null);
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.getByRegistration(anyString())).thenReturn(null);
        assertNotNull(carController.getByRegistration("dede"));
        assertEquals(404, carController.getByRegistration("momo").getStatusCodeValue());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/car/getByRegistration")
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                .andDo(document("{ClassName}/{methodName}"));
        //ResponseEntity response = ResponseEntity.notFound().build();
        /* response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
         assertEquals(response, carControllerImpl.getCarByRegistration(registration));*/

    }



    @Test
    @DisplayName("should return object if id is valid")
    void getById() throws Exception {
       // String name = carDTO.getId();
        int id = 3;
        when(carManager.getById(id)).thenReturn(carDTO);
        assertEquals(200, carController.getById(id).getStatusCodeValue());
        this.mockMvc.perform(
                get(urlItem + "/" + id)
                        .header("Authorization", "Bearer token")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
               // .andExpect(jsonPath("$.name").value(name))
               // .andExpect(jsonPath("$.id").value(id))
            /*    .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));*/

    }

    @Test
    @DisplayName("should return 404 if id is invalid")
    void getById1() throws Exception {
        int id =24;
        carManager = mock(CarManager.class);
        carController = new CarControllerImpl(carManager);
        when(carManager.getById(id)).thenReturn(null);
        assertEquals(404, carController.getById(id).getStatusCodeValue());
        System.out.println(carController.getById(id));
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/cars/"+id)
                        .header("Authorization", "Bearer test")
                        .content(converter.convertObjectIntoGsonObject(registration))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
                   .andExpect(content().contentType(encoding))
                .andDo(document("{ClassName}/{methodName}"));
        // ResponseEntity response = ResponseEntity.notFound().build();
        // ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(car));
        // assertEquals(response, carControllerImpl.getCarByRegistration(registration));
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

   /* @Test
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
    }*/
}