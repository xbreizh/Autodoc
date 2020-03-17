package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ClientServiceImpl extends GlobalServiceImpl<ClientDTO> implements ClientService {
    private static Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    Class getObjectClass() {
        return ClientDTO.class;
    }

    Class getListClass() {
        return ClientDTO[].class;
    }


/*    @Override
    public int update(String token, Object object) {
       // ClientDTO dto = (ClientDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<ClientDTO> requestUpdate = new HttpEntity<>((ClientDTO)object, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class).getStatusCodeValue();

    }*/

    /*@Override
    public String create(String token, Object object) {
        ClientDTO dto = (ClientDTO) object;
        setupHeader(token);
        String url = BASE_URL + getClassName();
        LOGGER.info("obj: " + object);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<ClientDTO> requestInsert = new HttpEntity<>(dto, headers);

        try {
            *//* return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Void.class).getStatusCodeValue();*//*
            // Integer response =
            return restTemplate.exchange(url, HttpMethod.POST, requestInsert, Integer.class).getBody().toString();
        } catch (RuntimeException error) {
            LOGGER.info(error.getLocalizedMessage().substring(0, 2));
            if (error.getClass().getSimpleName().equalsIgnoreCase("BadRequest")) {
                LOGGER.info("bam");
            }
            return error.getLocalizedMessage().substring(0, 3);
        }
    }*/


}

