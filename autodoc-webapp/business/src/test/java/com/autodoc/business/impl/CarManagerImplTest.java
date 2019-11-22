package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.contract.CarService;
import com.autodoc.impl.CarServiceImpl;
import com.autodoc.model.models.Car;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class CarManagerImplTest {

    private static final String BASE_URL = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI4OTQxNCwiaWF0IjoxNTc0MjcxNDE0fQ.vKKtg7YvrRIGOrfvXyvomQdLSP1Z5rnP4OidTli-qCta98jZ27KfMqekE8aH2Dg6ck5OpmoFKCmCjGuSn6BO_w";
    // String url = "http://localhost:8087/autodoc/employees";
    private CarManager carManager;
    //@Inject
    private CarService service;

    @BeforeEach
    void init() {
        service = new CarServiceImpl();
        carManager = new CarManagerImpl(service);
    }

    @Test
    void getByRegistration() {
        Car car = new Car();
        //when(service.getByRegistration(anyString(), anyString())).thenReturn(car);

        String registration = "05D154875";
        System.out.println("mgr: "+carManager);
        assertNotNull(carManager.getByRegistration(token, registration));
    }


    @Test
    void getById() {
        int id = 2;

        assertNotNull(carManager.getById(token, id));
    }


}