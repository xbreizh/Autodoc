package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
class EmployeeServiceImplTest {

    private EmployeeService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3Njc2MTg5MCwiaWF0IjoxNTc2NzQzODkwfQ.-7anXTdLUePoEIXn_dQhZDtaO7X1-gwZSiQakTehnfHJbsJZ106n1_vKSHhJjWjdE-7Onz8wLTi6TGyX55RyaQ";
    private EmployeeDTO dto;
    private Class clazz = EmployeeDTO.class;
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImplTest.class);

    @BeforeEach
    void init() {

        service = new EmployeeServiceImpl();
        dto = new EmployeeDTO();
        dto.setFirstName("John");
        dto.setLastName("Bonham");
        dto.setLogin("jbonhem");
        dto.setPassword("abc123");
        List<String> roles = new ArrayList<>();
        roles.add("MECANIC");
        dto.setRoles(roles);
        dto.setPhoneNumber1("029282726256");
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
        assertEquals(clazz, service.getAll(token).get(0).getClass());
    }


    @Test
    void getByid() {
        LOGGER.info("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        LOGGER.info("employee: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 2;
        EmployeeDTO employee = (EmployeeDTO) service.getById(token, id);
        String login = "MOLOK";
        employee.setLogin(login);
        employee.setStartDate(null);
        LOGGER.info(employee);
        service.update(token, employee);
        assertEquals(login, ((EmployeeDTO) service.getById(token, id)).getLogin());

    }


    @Test
    @DisplayName("should return 201 when insertion ok")
    void create() {
        assertEquals(201, service.create(token, dto));


    }

    @Test
    @DisplayName("should return 401 when insertion ko")
    void create1() {
        dto.setFirstName(null);
        assertEquals(400, service.create(token, dto));

    }


}