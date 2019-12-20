package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.contract.EnumService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.employee.ClientForm;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ClientManagerImpl extends GlobalManagerImpl<Client, ClientDTO> implements ClientManager {

    private static Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);


    ClientService service;
    EnumService enumService;


    public ClientManagerImpl(ClientService service, EnumService enumService) {
        super(service);
        this.service = service;
        this.enumService = enumService;
    }


    public Client dtoToEntity(String token, Object obj) {

        ClientDTO dto = (ClientDTO) obj;
        LOGGER.info("dto: " + dto);
        Client client = new Client();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        client.setId(id);
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber1(dto.getPhoneNumber1());
        LOGGER.info("entity transferred: " + client);

        return client;
    }

    public ClientDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        ClientForm dto = (ClientForm) obj;
        LOGGER.info("dto: " + dto);
        LOGGER.info(dto.getFirstName());
        ClientDTO client = new ClientDTO();
        if (dto.getId() != 0) client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber1(dto.getPhoneNumber1());
        LOGGER.info("entity transferred: " + client);
        return client;
    }

}
