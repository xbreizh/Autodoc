package com.autodoc.business.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagerImplTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String baseUrl="http://localhost:8087/autodoc/";


    @Test
    @DisplayName("should return a valid token")
    public void getEmployeeByLogin()
            throws IOException {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3MzkyNzAxOSwiaWF0IjoxNTczOTA5MDE5fQ.d_qD6geaj57wr_APOsrCmmWEQDQ9OB659AFO4L_9jC4RX-Rn7UyEwByzCar89pRU7snwyKAbHGQ2zaNv-x_NGQ";
        JSONObject jsonObject;
        RestTemplate restTemplate;

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        jsonObject = new JSONObject();
        jsonObject.put("username", "LMOLO");

        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);

        String employee =
                restTemplate.postForObject(baseUrl+"/employees", request, String.class);
        /*JsonNode root = objectMapper.readTree(token);
        System.out.println("tokent: "+employee);
        assertNotNull(token);
        assertNotNull(root);*/
        System.out.println("object returned: "+employee);
        //assertNotNull(root.path("name").asText());
    }
}