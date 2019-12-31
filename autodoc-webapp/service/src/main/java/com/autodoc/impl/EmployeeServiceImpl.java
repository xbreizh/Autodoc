package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.EmployeeList;
import com.autodoc.model.models.person.employee.Employee;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<EmployeeDTO> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return EmployeeDTO.class;
    }
    Class getListClass() {
        return EmployeeDTO[].class;
    }


    @Override
    public int update(String token, Object object) {
        EmployeeDTO dto = (EmployeeDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<EmployeeDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }

    @Override
    public int create(String token, Object object) {
        EmployeeDTO dto = (EmployeeDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<EmployeeDTO> requestInsert = new HttpEntity<>(dto, headers);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Void.class).getStatusCodeValue();
        } catch (RuntimeException error) {
            LOGGER.info(error.getLocalizedMessage().substring(0, 2));
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return Integer.parseInt(error.getLocalizedMessage().substring(0, 3));
        }
    }


}

