package com.autodoc.business.impl;


import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ClientManagerImpl extends GlobalManagerImpl<Client, ClientDTO> implements ClientManager {

    private static final String BASE_URL = "http://localhost:8087/autodoc/clients";
    private static final Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);

    private ClientService service;

    public ClientManagerImpl(ClientService service) {
        super(service);
    }


    public Client dtoToEntity(String token, Object obj) {

        ClientDTO dto = (ClientDTO) obj;
        System.out.println("dto: "+dto);
        Client client = new Client();
        client.setId(dto.getId());
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber1(dto.getPhoneNumber1());
        return client;
    }


  /*  @Override
    public Client getById(String token, int id) {
        LOGGER.info("trying to get client by login");
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
