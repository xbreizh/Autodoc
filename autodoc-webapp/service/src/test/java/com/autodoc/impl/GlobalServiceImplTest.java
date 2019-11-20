package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.Car;
import com.autodoc.model.Client;
import com.autodoc.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalServiceImplTest {


    private  GlobalService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI4OTQxNCwiaWF0IjoxNTc0MjcxNDE0fQ.vKKtg7YvrRIGOrfvXyvomQdLSP1Z5rnP4OidTli-qCta98jZ27KfMqekE8aH2Dg6ck5OpmoFKCmCjGuSn6BO_w";

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
    @DisplayName("should return an employee by Name")
    void testGetClassName() {
        service = new EmployeeServiceImpl();
        String login = "LMOLO";
        Employee employee = (Employee) service.getByName(token, login);
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