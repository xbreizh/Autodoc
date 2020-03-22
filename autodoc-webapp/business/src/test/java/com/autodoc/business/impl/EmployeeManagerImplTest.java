package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.contract.EnumService;
import com.autodoc.impl.EmployeeServiceImpl;
import com.autodoc.impl.EnumServiceImpl;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagerImplTest {
    private static final Logger LOGGER = Logger.getLogger(EmployeeManagerImplTest.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjUzNDM5MCwiaWF0IjoxNTc2NTE2MzkwfQ.r64Ed4Df6I8ZbL8LK9p4ZLDye_kH5UcwhhbZaS4HQQXbDLzE1Z6dTo7Bn51qHyzpZJE1MpDl-wJSTEPq_ytBUA";
    private EmployeeManager employeeManager;
    private EmployeeService service;

    private EnumService enumService;


    @BeforeEach
    void init() {

        service = new EmployeeServiceImpl();
        enumService = new EnumServiceImpl();
        employeeManager = new EmployeeManagerImpl(service, enumService);
    }

    @Test
    @DisplayName("should return a valid employee")
    public void getEmployeeByLogin() {
        System.out.println("token: " + token);
        String login = "LMOLO";
        assertNotNull(employeeManager.getByLogin(token, login));

    }

    @Test
    @DisplayName("should return a valid employee")
    public void getEmployeeById()
            throws Exception {

        int id = 1;

        assertNotNull(employeeManager.getById(token, id));

    }

    @Test
    @DisplayName("should return a valid employee")
    public void getEmployeeById1()
            throws Exception {

        int id = 3333331;
        LOGGER.info("dede: " + employeeManager.getById(token, id));
        assertNull(employeeManager.getById(token, id));

    }


    @Test
    void cleanToken() {
        String crypt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDExNDU5OCwiaWF0IjoxNTc0MDk2NTk4fQ.NQ0-Cg5lmlQNLxFAmtPYyGstNPHaob43xeYJ7q27MZkKAPnKrmG7eGFIbP4iPsnAbpsKIT-XLKhHCsrRGK7zPw";
        String token = "{\"token\":\"" + crypt + "\"}";
        String newToken = token.replace("{\"token\":\"", "");
        newToken = newToken.replace("\"}", "");
        assertEquals(crypt, newToken);
    }

    @Test
    void getEmployees() throws Exception {

        assertNotNull(employeeManager.getAll(token));
    }

    @Test
    void getEmployeeList() throws Exception {
        assertNotNull(employeeManager.getAll(token));
    }
}