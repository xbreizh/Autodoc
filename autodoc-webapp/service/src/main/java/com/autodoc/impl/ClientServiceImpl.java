package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.model.dtos.person.client.ClientDTO;

import javax.inject.Named;

@Named
public class ClientServiceImpl extends GlobalServiceImpl<ClientDTO> implements ClientService {

    Class getObjectClass() {
        return ClientDTO.class;
    }

    Class getListClass() {
        return ClientDTO[].class;
    }


}

