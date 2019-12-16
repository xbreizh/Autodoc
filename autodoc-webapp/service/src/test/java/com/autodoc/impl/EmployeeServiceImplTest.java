package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
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
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjUyNjU1OSwiaWF0IjoxNTc2NTA4NTU5fQ.1jvALQZqzsY23A8jOSbjI9qkkHnTTlog4srFzOH1p0P3SEa2kI2tZ7NpubgmZlCNN_TSdjSG-g0nuhTj5izjOQ";
    private EmployeeDTO dto;


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
        employee.setStartDate(null);
        System.out.println(employee);
        service.update(token, employee);
        assertEquals(login, ((EmployeeDTO) service.getById(token, id)).getLogin());

    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
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