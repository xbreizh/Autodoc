package com.autodoc.business.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.dao.impl.ClientDaoImpl;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class ClientManagerImpl implements ClientManager {
    private ClientDaoImpl<Client> clientDao;
    private Logger logger = Logger.getLogger(ClientManagerImpl.class);


    public ClientManagerImpl(ClientDaoImpl<Client> clientDao) {
        this.clientDao = clientDao;

    }


    @Override
    public String  save(Client client) {
        clientDao.create(client);
        return "car added";
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
