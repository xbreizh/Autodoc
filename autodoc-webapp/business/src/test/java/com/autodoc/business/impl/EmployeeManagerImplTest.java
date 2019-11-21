package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.impl.EmployeeServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeManagerImplTest {
    private static final String baseUrl = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDI3MTMyNiwiaWF0IjoxNTc0MjUzMzI2fQ.jgs9QiIhXKF2Li6VY6PwaVYb6ubdtyYEw-MbJLzO3zIszR5mkArZeVznXSkU1v5lk7yKvxMVeJgoJ7BGL0U4aA";
    // String url = "http://localhost:8087/autodoc/employees";
    private EmployeeManager employeeManager;
    //@Inject
    private EmployeeService service;

    @BeforeEach
    void init() {
        service = new EmployeeServiceImpl();
        employeeManager = new EmployeeManagerImpl(service);
    }

    @Test
    @DisplayName("should return a valid employee")
    public void getEmployeeByLogin()
            throws IOException {

        String login = "LMOLO";

       assertNotNull(employeeManager.getByLogin(token, login));

    }

    @Test
    @DisplayName("should return a valid employee")
    public void getEmployeeById()
            throws IOException {

        int id =2;

        assertNotNull(employeeManager.getById(token, id));

    }



    @Test
    void cleanToken(){
        String crypt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDExNDU5OCwiaWF0IjoxNTc0MDk2NTk4fQ.NQ0-Cg5lmlQNLxFAmtPYyGstNPHaob43xeYJ7q27MZkKAPnKrmG7eGFIbP4iPsnAbpsKIT-XLKhHCsrRGK7zPw";
        String token = "{\"token\":\""+crypt+"\"}";
        String newToken=token.replace("{\"token\":\"", "");
        newToken = newToken.replace("\"}", "");
        assertEquals(crypt, newToken);
    }

    @Test
    void getEmployee() {
    }

    @Test
    void getEmployeeList() {
        assertNotNull(employeeManager.getAll(token));
    }
}