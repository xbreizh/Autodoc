package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.HttpClientErrorException;
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
        LOGGER.info("setting up");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>(headers);
    }


    @Override
    public Object getById(String token, int id) {
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
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
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
    public Object getByName(String token, String name) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("login: " + name);
            String className = getClassName();
            String url = BASE_URL + className + "/name?name=" + name;
            LOGGER.info("url: " + url);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
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
    public List getAll(String token) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            String className = getClassName();
            String url = BASE_URL + className;
        /*    LOGGER.info("url: "+url);
          //  ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);
            ResponseEntity<Object[]> res =restTemplate.exchange(url, HttpMethod.GET, request, Object[].class);
            // res = (T) res;
          //  LOGGER.info("res: "+restTemplate.exchange(url, HttpMethod.GET, request, Employee[].class));
            LOGGER.info(res.getBody().toString());*/
            LOGGER.info(ArrayList.class);
            ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, request, ArrayList.class);
            LOGGER.info("result: " + response.getBody().get(0));
            LOGGER.info("result: " + response.getBody().get(1));


            List<T> newList = new ArrayList<>();
            for (Object obj : response.getBody()) {
                obj = obj;
                newList.add((T) obj);
            }

            return newList;
          /*  LOGGER.info(restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass()));
            ResponseEntity<Object[]> res = restTemplate.exchange(url, HttpMethod.GET, request, Object[].class);
            LOGGER.info("deded: " + res.getBody());
            //T[] objects = res.getBody();*/
            // LOGGER.info("obj: "+objects);
            //return Arrays.asList(objects);
            //return null;
        } catch (Exception e) {
            LOGGER.info("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public int create(String token, Object object) {
        return 0;
    }

    @Override
    public int update(String token, Object object) {
        return 0;
    }

    @Override
    public int delete(String token, int id) {
        return 0;
    }

    @Override
    public List getByCriteria(String token, Map criteria) {
        return null;
    }
}
