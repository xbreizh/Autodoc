package com.autodoc.business.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public abstract class GlobalManager {

    private static final String BASE_URL = "http://localhost:8087/autodoc/cars";
    RestTemplate restTemplate;
    HttpEntity<?> request;

    void setupHeader(String token){
        System.out.println("setting up");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>( headers);
    }
}
