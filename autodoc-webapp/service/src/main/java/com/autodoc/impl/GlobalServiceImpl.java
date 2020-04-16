package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.SearchDto;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GlobalServiceImpl<D> implements GlobalService {

    static final String BASE_URL = "http://localhost:8087/autodoc/";
    private static final Logger LOGGER = Logger.getLogger(GlobalServiceImpl.class);
    RestTemplate restTemplate;
    HttpEntity<?> request;

    public GlobalServiceImpl() {

    }


    public String getClassName() {
        String className = getObjectClass().getSimpleName().toLowerCase();
        String newClassname = className.replaceAll("dto", "s");
        return newClassname;
    }

    Class getObjectClass() {
        return null;
    }

    Class getListClass() {
        return null;
    }


    @Override
    public D getById(String token, int id) {
        LOGGER.info("trying to get object by id");
        setupHeader(token);
        try {
            String className = getClassName();
            String url = BASE_URL + className + "/" + id;
            ResponseEntity<D> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            System.out.println("response: "+response);
            if (response.getStatusCodeValue() == 404) return null;
            return response.getBody();
        } catch (HttpClientErrorException.NotFound exception) {
            LOGGER.info(exception.getMessage());
            LOGGER.info(exception.getClass().getCanonicalName());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getClass());
            System.out.println("wot: "+e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
        return null;
    }

    @Override
    public D getByName(String token, String name) {
        setupHeader(token);
        try {
            String className = getClassName();
            String url = BASE_URL + className + "/name?name=" + name;
            ResponseEntity<D> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            if (response.getStatusCodeValue() == 404) return null;
            return response.getBody();
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


    @Override
    public List<D> getAll(String token) {
        setupHeader(token);
        try {
            String className = getClassName();
            String url = BASE_URL + className;
            ResponseEntity<D[]> response = restTemplate.exchange(url, HttpMethod.GET, request, getListClass());

            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            LOGGER.info("error occurred: " + e.getCause());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


    @Override
    public List<D> getByCriteria(String token, SearchDto searchDto) {
        HttpHeaders headers = setupHeader(token);
        try {
            String className = getClassName();
            String url = BASE_URL + className + "/criteria";

            String criteria = searchDto.toString();
            HttpEntity<String> request = new HttpEntity<>(criteria, headers);
            ResponseEntity<D[]> response = restTemplate.exchange(url, HttpMethod.POST, request, getListClass());
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            LOGGER.info("error occurred: " + e.getCause());
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }


    @Override
    public String create(String token, Object object) {
        HttpMethod method = HttpMethod.POST;
        return globalCall(token, object, method);
    }


    private String globalCall(String token, Object object, HttpMethod method) {
        HttpHeaders headers = setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);

        try {
            return restTemplate.exchange(url, method, new HttpEntity<>((D) object, headers), String.class).getBody();
        } catch (HttpClientErrorException error) {
            String errorDetails = error.getRawStatusCode() + " / " + error.getResponseBodyAsString();
            LOGGER.info(errorDetails);
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return errorDetails;
        }
    }

    protected HttpHeaders setupHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
        return headers;
    }

    @Override
    public String update(String token, Object object) {
        return globalCall(token, object, HttpMethod.PUT);
    }

    @Override
    public int delete(String token, int id) {
        HttpHeaders headers = setupHeader(token);
        LOGGER.info("trying to delete object by id");

        String className = getClassName();
        String url = BASE_URL + className + "/" + id;

        restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(headers), String.class);
        return 0;
    }


    @Override
    public void filler() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
        try {
            LOGGER.info("restTemplate ready");
            String url = BASE_URL + "filler";
            LOGGER.info(ArrayList.class);

            restTemplate.exchange(url, HttpMethod.GET, request, Void.class);
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }

    @Override
    public void reset() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
        try {
            LOGGER.info("restTemplate ready");
            String url = BASE_URL + "reset";
            LOGGER.info(ArrayList.class);

            restTemplate.exchange(url, HttpMethod.GET, request, Void.class);
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }
}
