package com.autodoc.impl;

import com.autodoc.contract.EmployeeService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
public class EmployeeServiceImpl extends GlobalServiceImpl<Employee> implements EmployeeService {
    private static Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    Class getObjectClass() {
        return EmployeeDTO.class;
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
        List<String> roles = new ArrayList<>();
        roles.add("MECANIC");
        dto.setRoles(roles);
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<EmployeeDTO> requestInsert = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Void.class).getStatusCodeValue();
    }


}

