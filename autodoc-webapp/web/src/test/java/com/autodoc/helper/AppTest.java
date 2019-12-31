package com.autodoc.helper;


import com.autodoc.impl.BillServiceImpl;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    //String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3MzY4MzE5MSwiaWF0IjoxNTczNjY1MTkxfQ.JCMPJCIv3SaKAR2Kc_I0ydkhRsw3KkAl1wkjuwU01lU6KLkNxLWuu6aqRsMUuBh5lLwWh90dAjlic6Xkky_feg";

    private static JSONObject personJsonObject;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String baseUrl="http://localhost:8087/autodoc/";
    private static Logger LOGGER = Logger.getLogger(AppTest.class);


    @Test
    void checkLog(){
        assertNotNull(LOGGER);
        LOGGER.info("log ok");
    }

    @Test
    @DisplayName("should return a valid token")
    public void authenticate()
            throws IOException {

        RestTemplate restTemplate;

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("username", "LMOLO");
        personJsonObject.put("password", "password");

        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        String token =
                restTemplate.postForObject(baseUrl+"/authenticate", request, String.class);
        JsonNode root = objectMapper.readTree(token);
        System.out.println("tokent: "+token);
        assertNotNull(token);
        assertNotNull(root);
        //assertNotNull(root.path("name").asText());
    }

    @Test
    @DisplayName("should return \"INVALID CREDENTIALS\" if invalid token")
    public void authenticate2()throws IOException {
        RestTemplate restTemplate;

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("username", "LMddOLO");
        personJsonObject.put("password", "password");

        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        assertThrows(HttpClientErrorException.class, ()-> restTemplate.postForObject(baseUrl+"/authenticate", request, String.class));

    }



}