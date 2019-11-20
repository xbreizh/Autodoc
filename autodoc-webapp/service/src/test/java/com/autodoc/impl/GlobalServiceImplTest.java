package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.Car;
import com.autodoc.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalServiceImplTest {


    private  GlobalService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI3MTMyNiwiaWF0IjoxNTc0MjUzMzI2fQ.jgs9QiIhXKF2Li6VY6PwaVYb6ubdtyYEw-MbJLzO3zIszR5mkArZeVznXSkU1v5lk7yKvxMVeJgoJ7BGL0U4aA";

    @BeforeEach
    void init(){

    }

    @Test
    void getClassName() {
        service = new GlobalServiceImpl();
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
        /*System.out.println(service);
        Client client = (Client) service.getById(token, id);
        System.out.println("client: "+client);*/
        Client client = (Client) service.getById(token, id);
        System.out.println(client);
        assertNotNull(service.getById(token, id));
    }

    @Test
    void testGetClassName() {
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