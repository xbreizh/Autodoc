package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GlobalServiceImpl<T> implements GlobalService {

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

    void setupHeader(String token) {
        System.out.println("setting up");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
    }


    @Override
    public Object getById(String token, int id) {
        LOGGER.info("trying to get car by id");
        setupHeader(token);

        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("id: " + id);
            String className = getClassName();
            String url = BASE_URL + className + "/" + id;
            LOGGER.info("url: " + url);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            LOGGER.info("stop");
            System.out.println("req: " + request);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public Object getByName(String token, String name) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("login: " + name);
            String className = getClassName();
            String url = BASE_URL + className + "/name?name=" + name;
            System.out.println("url: " + url);
            ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            System.out.println("deded: " + res.getBody());
            return res.getBody();
        } catch (Exception e) {
            System.out.println("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public List getAll(String token) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            String className = getClassName();
            String url = BASE_URL + className;
        /*    System.out.println("url: "+url);
          //  ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);
            ResponseEntity<Object[]> res =restTemplate.exchange(url, HttpMethod.GET, request, Object[].class);
            // res = (T) res;
          //  System.out.println("res: "+restTemplate.exchange(url, HttpMethod.GET, request, Employee[].class));
            System.out.println(res.getBody().toString());*/
            System.out.println(ArrayList.class);
            ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, request, ArrayList.class);
            System.out.println("result: " + response.getBody().get(0));
            System.out.println("result: " + response.getBody().get(1));


            List<T> newList = new ArrayList<>();
            for (Object obj : response.getBody()) {
                obj = obj;
                newList.add((T) obj);
            }

            return newList;
          /*  System.out.println(restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass()));
            ResponseEntity<Object[]> res = restTemplate.exchange(url, HttpMethod.GET, request, Object[].class);
            System.out.println("deded: " + res.getBody());
            //T[] objects = res.getBody();*/
            // System.out.println("obj: "+objects);
            //return Arrays.asList(objects);
            //return null;
        } catch (Exception e) {
            System.out.println("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List getByCriteria(String token, Map criteria) {
        return null;
    }
}
