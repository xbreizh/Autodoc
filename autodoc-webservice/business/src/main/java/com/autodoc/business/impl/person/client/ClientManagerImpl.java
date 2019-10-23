package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ClientManagerImpl<T, D> extends AbstractGenericManager implements ClientManager {
    private Logger logger = Logger.getLogger(ClientManagerImpl.class);
    private ClientDaoImpl clientDao;

    public ClientManagerImpl(ClientDaoImpl clientDao) {
        super(clientDao);
        this.clientDao = clientDao;

    }


    @Override
    public ClientDTO getByName(String name) {
        return entityToDto(clientDao.getByName(name));
    }


    @Override
    public ClientDTO entityToDto(Object entity) {
        Client client = (Client) entity;
        /*ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setId(client.getId());
        clientDTO.setPhoneNumber1(client.getPhoneNumber1());
        clientDTO.setPhoneNumber2(client.getPhoneNumber2());
        return clientDTO;*/
        return null;
    }

    @Override
    public Client dtoToEntity(Object entity) {
        ClientDTO dto = (ClientDTO) entity;
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber1(dto.getPhoneNumber1());
        client.setPhoneNumber2(dto.getPhoneNumber2());
        return client;
    }


}
