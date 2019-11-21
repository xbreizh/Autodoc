package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalServiceImplTest {


    private  GlobalService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDM2MDgxNywiaWF0IjoxNTc0MzQyODE3fQ.GbMKAFeTXG3ThuZGE0W5hQqYB3x5rB9jTrFwfU9D62oZoE_jPaKiR7p3CF2KSBpJFUrtxORV8XKHUMPCktAWXw";

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