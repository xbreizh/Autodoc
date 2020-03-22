package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalServiceImplTest extends HelperTest {


    private GlobalService service;

    private static final Logger LOGGER = Logger.getLogger(GlobalServiceImplTest.class);

    @BeforeEach
    void init() {
        this.token = getToken();

    }

    @Test
    @DisplayName("should return cars")
    void getClassName() {
        service = new CarServiceImpl();
        LOGGER.info(service.getClassName());
        assertEquals("cars", service.getClassName());


    }

    @Test
    @DisplayName("should return carModels")
    void getClassName1() {
        service = new CarModelServiceImpl();
        LOGGER.info(service.getClassName());
        assertEquals("carModels", service.getClassName());


    }


    @Test
    void setupHeader() {
    }

    @Test
    @DisplayName("should return a car object")
    void getCarById() {
        service = new CarServiceImpl();
        int id = 1;
        LOGGER.info(service);
        CarDTO carDTO = (CarDTO) service.getById(token, id);
        LOGGER.info("cars: " + carDTO);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return null if id invalid")
    void getCarById1() {
        service = new CarServiceImpl();
        int id = 1222;
        assertNull(service.getById(token, id));
    }


    @Test
    @DisplayName("should return a manufacturer object")
    void getManufacturerById() {
        service = new ManufacturerServiceImpl();
        int id = 1;
        LOGGER.info(service);
        ManufacturerDTO dto = (ManufacturerDTO) service.getById(token, id);
        LOGGER.info("manufacturers: " + dto);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return a client object")
    void getClientById() {
        service = new ClientServiceImpl();
        int id=1;
        ClientDTO client = (ClientDTO) service.getById(token, id);
        LOGGER.info(client);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return an employee by Name")
    void testGetClassName() {
        service = new EmployeeServiceImpl();
        String login = "LMOLO";
        EmployeeDTO employee = (EmployeeDTO) service.getByName(token, login);
        LOGGER.info(employee);
        assertNotNull(employee);
    }

    @Test
    void getByName() {
    }

    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getByCriteria() {
    }
}