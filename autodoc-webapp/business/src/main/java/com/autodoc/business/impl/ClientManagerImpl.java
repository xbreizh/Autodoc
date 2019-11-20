package com.autodoc.business.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.model.Car;
import com.autodoc.model.Client;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import javax.inject.Named;

@Named
public class ClientManagerImpl extends GlobalManager implements ClientManager {

    private static final String BASE_URL = "http://localhost:8087/autodoc/clients";
    private static final Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);



    @Override
    public Client getById(String token, int id) {
        LOGGER.info("trying to get employee by login");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("id: " + id);
            ResponseEntity<Client> res = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.GET, request, Client.class);
            System.out.println("deded: " + res.getBody());
            return res.getBody();
        } catch (Exception e) {
            System.out.println("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }


}
