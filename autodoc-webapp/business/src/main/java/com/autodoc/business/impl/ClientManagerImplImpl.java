package com.autodoc.business.impl;


import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.model.models.Client;
import com.autodoc.model.dtos.ClientDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ClientManagerImplImpl extends GlobalManagerImpl<Client, ClientDTO> implements ClientManager {

    private static final String BASE_URL = "http://localhost:8087/autodoc/clients";
    private static final Logger LOGGER = Logger.getLogger(ClientManagerImplImpl.class);

    private ClientService service;

    public ClientManagerImplImpl(ClientService service) {
        super(service);
    }





  /*  @Override
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
    }*/


}
