package com.autodoc.impl;

import com.autodoc.contract.EnumService;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
public class EnumServiceImpl implements EnumService {


    static final String BASE_URL = "http://localhost:8087/autodoc/";
    private static final Logger LOGGER = Logger.getLogger(GlobalServiceImpl.class);
    RestTemplate restTemplate;
    HttpEntity<?> request;

    public EnumServiceImpl() {
    }

    void setupHeader(String token) {
        LOGGER.info("setting up");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
    }

    @Override
    public List<String> getAll(String token, String enumValue) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            // String className = getClassName();
            String url = BASE_URL + enumValue;
            LOGGER.info(ArrayList.class);
            ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, request, ArrayList.class);
            LOGGER.info("result: " + response.getBody().get(0));


            List<String> newList = new ArrayList<>();
            String s = response.getBody().toString();
            String[] enumList = s.split(",");

            for (String t : enumList) {
                String v = t.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
                newList.add(v);
            }
            return newList;
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }
}
