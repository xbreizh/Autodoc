package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalServiceImplTest {


    private  GlobalService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDcxMjY5MCwiaWF0IjoxNTc0Njk0NjkwfQ.6fFaipPO2S46l0vORn-eo2uJQKLNLghgtNIJ6KE_cBWm8CloPbGrOUv7-DWrnsb5-gqpOOamugNBe7CJ12-e1w";

    @BeforeEach
    void init(){

    }

    @Test
    void getClassName() {
        service = new CarServiceImpl();
        System.out.println(service.getClassName());
        assertEquals("cars", service.getClassName());


    }

    @Test
    void setupHeader() {
    }

    @Test
    @DisplayName("should return a car object")
    void getCarById() {
        service = new CarServiceImpl();
        int id=1;
        System.out.println(service);
        Car car = (Car) service.getById(token, id);
        System.out.println("car: "+car);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return a client object")
    void getClientById() {
        service = new ClientServiceImpl();
        int id=1;
        ClientDTO client = (ClientDTO) service.getById(token, id);
        System.out.println(client);
        assertNotNull(service.getById(token, id));
    }

    @Test
    @DisplayName("should return an employee by Name")
    void testGetClassName() {
        service = new EmployeeServiceImpl();
        String login = "LMOLO";
        EmployeeDTO employee = (EmployeeDTO) service.getByName(token, login);
        System.out.println(employee);
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