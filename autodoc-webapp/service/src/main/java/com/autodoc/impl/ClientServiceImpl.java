package com.autodoc.impl;

import com.autodoc.contract.ClientService;
import com.autodoc.model.Client;

import javax.inject.Named;

@Named
public class ClientServiceImpl extends GlobalServiceImpl<Client> implements ClientService {

    Class getObjectClass() {
        return Client.class;
    }


}
