package com.autodoc.impl;

import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.bill.BillDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.inject.Named;
import java.util.Collections;

@Named
public class BillServiceImpl extends GlobalServiceImpl<BillDTO> implements BillService {
    private static Logger LOGGER = Logger.getLogger(BillServiceImpl.class);

    Class getObjectClass() {
        return BillDTO.class;
    }

    Class getListClass() {
        return BillDTO[].class;
    }

    public String getClassName() {
        return "bills";
    }


    @Override
    public int update(String token, Object object) {
        BillDTO dto = (BillDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<BillDTO> requestUpdate = new HttpEntity<>(dto, headers);
        LOGGER.info("body: " + restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getBody());
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }


    @Override
    public String create(String token, Object object) {
        BillDTO dto = (BillDTO) object;
        LOGGER.info("class: " + getClassName());
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<BillDTO> requestInsert = new HttpEntity<>(dto, headers);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Integer.class).getBody().toString();
        } catch (RuntimeException error) {
            LOGGER.info("er: " + error.getLocalizedMessage());
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
            }
            return error.getLocalizedMessage().substring(0, 3);
        }
    }


}

