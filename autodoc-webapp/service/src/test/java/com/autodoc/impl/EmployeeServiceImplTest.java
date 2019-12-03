package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeServiceImplTest {

    private EmployeeService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NTM4NDA0NCwiaWF0IjoxNTc1MzY2MDQ0fQ.W-0cTbXBJRDkd35w4QTHFMhp5iMwOgP92RCuh63rx8XsydsWdwAosipkAszR8hJL8uVE00K5Om5dI1Xu2c-qMA";

    @BeforeEach
    void init() {
        service = new EmployeeServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByRegistration() {
        String login = "LOKII";
        assertNotNull(service.getByName(token, login));
    }

    @Test
    void getByid() {
        System.out.println("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        System.out.println("employee: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 2;
        EmployeeDTO employee = (EmployeeDTO) service.getById(token, id);
        String login = "MOLOK";
        employee.setLogin(login);
        service.update(token, employee);
        //assertEquals(login, ((EmployeeDTO) service.getById(token, id)).getLogin());
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
    }
}