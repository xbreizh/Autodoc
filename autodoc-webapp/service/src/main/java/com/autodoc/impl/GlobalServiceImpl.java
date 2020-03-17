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
    public D getById(String token, int id) {
        LOGGER.info("trying to get object by id");
        setupHeader(token);


        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("id: " + id);
            String className = getClassName();
            String url = BASE_URL + className + "/" + id;
            LOGGER.info("url: " + url);
            LOGGER.info("mokoro: " + restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass()));
            ResponseEntity<D> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            LOGGER.info("resp: " + response.getStatusCodeValue());
            if (response.getStatusCodeValue() == 404) return null;
            LOGGER.info("stop");
            LOGGER.info("req: " + request);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound exception) {
            LOGGER.info(exception.getMessage());
            LOGGER.info(exception.getClass().getCanonicalName());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
        return null;
    }

    @Override
    public D getByName(String token, String name) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("login: " + name);
            String className = getClassName();
            String url = BASE_URL + className + "/name?name=" + name;
            LOGGER.info("url: " + url);
            ResponseEntity<D> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            if (response.getStatusCodeValue() == 404) return null;
            LOGGER.info("deded: " + response.getBody());
            return response.getBody();
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


    @Override
    public List<D> getAll(String token) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            String className = getClassName();
            String url = BASE_URL + className;
            LOGGER.info("list class: " + getListClass().toString());
            ResponseEntity<D[]> response = restTemplate.exchange(url, HttpMethod.GET, request, getListClass());
            LOGGER.info("resp: " + response.getBody());
            LOGGER.info(response.getBody()[0]);

            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


    @Override
    public List<D> getByCriteria(String token, SearchDto searchDto) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            String className = getClassName();
            String url = BASE_URL + className + "/criteria";
            LOGGER.info("list class: " + getListClass().toString());
            System.out.println(request.toString());
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            String crit = searchDto.toString();
            HttpEntity<String> request = new HttpEntity<>(crit, headers);
            System.out.println(request);
            ResponseEntity<D[]> response = restTemplate.exchange(url, HttpMethod.POST, request, getListClass());
            System.out.println("response: " + response.getBody());
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }


    @Override
    public String create(String token, Object object) {
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>((D) object, headers), String.class).getBody();
        } catch (HttpClientErrorException error) {
            String errorDetails = error.getResponseBodyAsString();
            LOGGER.info(errorDetails);
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return errorDetails;
        }
    }

    @Override
    public int update(String token, Object object) {
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>((D) object, headers), Void.class).getStatusCodeValue();

    }

    @Override
    public int delete(String token, int id) {
        LOGGER.info("trying to delete object by id");
        setupHeader(token);


        String className = getClassName();
        String url = BASE_URL + className + "/" + id;

        HttpHeaders header = new HttpHeaders();
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(token);
        restTemplate.exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(header), String.class);
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
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, request, Void.class);


        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }
}
