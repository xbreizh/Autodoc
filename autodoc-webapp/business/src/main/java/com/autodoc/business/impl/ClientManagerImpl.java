package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.ClientService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.client.ClientForm;
import com.autodoc.model.models.person.client.Client;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
@Builder
public class ClientManagerImpl extends GlobalManagerImpl<Client, ClientDTO> implements ClientManager {

    private static final Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);
    ClientService service;

    GlobalService getService() {
        return service;
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
        client.setPhoneNumber(dto.getPhoneNumber());
        LOGGER.info("entity transferred: " + client);

        return client;
    }

    public ClientDTO formToDto(Object obj, String token) {
        ClientForm form = (ClientForm) obj;
        LOGGER.info("form: " + form);
//        LOGGER.info(form.getFirstName());
        ClientDTO client = new ClientDTO();
        if (form.getId() != 0) client.setId(form.getId());
        client.setFirstName(form.getFirstName());
        client.setLastName(form.getLastName());
        client.setPhoneNumber(form.getPhoneNumber());
        LOGGER.info("entity transferred: " + client);
        return client;
    }


}
