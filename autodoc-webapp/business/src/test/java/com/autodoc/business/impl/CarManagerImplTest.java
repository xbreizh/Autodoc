package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.BillService;
import com.autodoc.contract.CarService;
import com.autodoc.impl.CarServiceImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.CarForm;
import com.autodoc.model.dtos.person.client.ClientForm;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarManagerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImplTest.class);
    // private static final String BASE_URL = "http://localhost:8087/autodoc/";
    // private final ObjectMapper objectMapper = new ObjectMapper();
    //   private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk2ODA1MCwiaWF0IjoxNTc0OTUwMDUwfQ.BfKxkfEVLatJRdOfVph7XqJHnCBWPGFKyaq2exJNUYFMr9lU0-EgQIQ_vecx8oqQIV1GEJ3vP8mSjUhu1vfrdA";
    // String url = "http://localhost:8087/autodoc/employees";
    private String token = "token";
    private CarManager manager;
    //@Inject
    private CarService service;
    // @Inject
    private ClientManager clientManager;
    //  @Inject
    private CarModelManager carModelManager;
    //  @Inject
    //private EnumService enumService;
    private ManufacturerManager manufacturerManager;
    // @Inject
    private BillService billService;

    @BeforeEach
    void init() {
        service = mock(CarServiceImpl.class);
        clientManager = mock(ClientManager.class);
        manufacturerManager = mock(ManufacturerManagerImpl.class);
        carModelManager = mock(CarModelManagerImpl.class);
        manager = new CarManagerImpl(service, carModelManager, clientManager, billService);
    }


    @Test
    @DisplayName("should return car id")
    void addNewCar() throws Exception {
        CarForm form = new CarForm();
        ClientForm clientForm = new ClientForm("bob", "chanteal", "mazoki");
        form.setClient(clientForm);
        form.setModelId(1);
        String carId = "12";
        String clientFeedback = "400 / client already exist";
        form.setRegistration("deferfr33");
        when(clientManager.add(anyString(), any(ClientForm.class))).thenReturn("23");
        when(service.create(anyString(), any(CarDTO.class))).thenReturn(carId);
        assertEquals(carId, manager.addNewCar(token, form));

    }

    @Test
    @DisplayName("should return client creation error")
    void addNewCar1() throws Exception {
        CarForm form = new CarForm();
        ClientForm clientForm = new ClientForm("bob", "chanteal", "mazoki");
        form.setClient(clientForm);
        form.setModelId(1);
        String clientFeedbackCode = "400";
        String clientFeedbackDetails = "client already exists";
        String clientFeedback = clientFeedbackCode + " / \"" + clientFeedbackDetails + "\"";
        form.setRegistration("deferfr33");
        when(clientManager.add(anyString(), any(ClientForm.class))).thenReturn(clientFeedback);
        assertEquals(clientFeedbackDetails, manager.addNewCar(token, form));

    }


    @Test
    void getByRegistration() throws Exception {
        Car car = new Car();
        //when(service.getByRegistration(anyString(), anyString())).thenReturn(car);

        String registration = "05D154875";
        LOGGER.info("mgr: " + manager);
        assertNotNull(manager.getByRegistration(token, registration));
    }


    @Test
    void getById() throws Exception {
        int id = 2;

        assertNotNull(manager.getById(token, id));
    }


}