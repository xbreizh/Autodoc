package com.autodoc.impl;

import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.inject.Named;
import java.util.Collections;

@Named
public class ProviderServiceImpl extends GlobalServiceImpl<ProviderDTO> implements ProviderService {
    private static Logger LOGGER = Logger.getLogger(ProviderServiceImpl.class);

    Class getObjectClass() {
        return ProviderDTO.class;
    }
    Class getListClass() {
        return ProviderDTO[].class;
    }

    public String getClassName() {
        return "providers";
    }


    @Override
    public int update(String token, Object object) {
        ProviderDTO dto = (ProviderDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<ProviderDTO> requestUpdate = new HttpEntity<>(dto, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }


    @Override
    public int create(String token, Object object) {
        ProviderDTO dto = (ProviderDTO) object;
        System.out.println("class: " + getClassName());
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<ProviderDTO> requestInsert = new HttpEntity<>(dto, headers);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Void.class).getStatusCodeValue();
        } catch (RuntimeException error) {
            System.out.println("er: " + error.getLocalizedMessage());
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
            }
            return Integer.parseInt(error.getLocalizedMessage().substring(0, 3));
        }
    }


}

