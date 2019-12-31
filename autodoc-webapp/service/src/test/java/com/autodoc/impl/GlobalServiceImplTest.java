package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalServiceImplTest {


    private GlobalService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NTA1OTQ1OSwiaWF0IjoxNTc1MDQxNDU5fQ.gJCEsF1qgI-USKDeUW952b-K8P-hoiJYHYbuBrbQyaXFm7z7lXu2iIq_7R5_BzKwKd1ARc05crxNvfio9zojeQ";

    @BeforeEach
    void init(){

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