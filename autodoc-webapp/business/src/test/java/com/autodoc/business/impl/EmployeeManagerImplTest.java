package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeManagerImplTest {
    private static final String baseUrl = "http://localhost:8087/autodoc/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDE4MzM0OSwiaWF0IjoxNTc0MTY1MzQ5fQ.2NvcsD73cI1m-wosIPHgCDEUk6BbQW93-kZOBYWxoWiBTuUr4VXCcfq9nac5izm5wRO9f07GbXTc0eiNGi7J8Q";
    // String url = "http://localhost:8087/autodoc/employees";
    private EmployeeManager employeeManager;

    @BeforeEach
    void init() {
        employeeManager = new EmployeeManagerImpl();
    }

    @Test
    @DisplayName("should return a valid token")
    public void getEmployeeByLogin()
            throws IOException {

        String login = "LMOLO";

       assertNotNull(employeeManager.getEmployee(token, login));

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
        assertNotNull(employeeManager.getEmployeeList(token));
    }
}