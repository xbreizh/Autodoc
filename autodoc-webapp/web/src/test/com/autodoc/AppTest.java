package com.autodoc;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {


    @Test
    public void appTest() {


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("", ""));
        String fooResourceUrl
                = "http://localhost:8087/autodoc/filler";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        LOGGER.info(restTemplate.getInterceptors().get(0).toString());
        LOGGER.info("response code: " + response.getStatusCode());
        assertEquals(201, response.getStatusCodeValue());
    }

}