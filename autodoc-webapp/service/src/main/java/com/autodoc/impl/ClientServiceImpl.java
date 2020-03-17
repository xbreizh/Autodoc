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



}

