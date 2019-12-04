package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ClientManagerImpl<T, D> extends AbstractGenericManager implements ClientManager {
    private static final Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);
    private ClientDaoImpl clientDao;
    private ModelMapper mapper;

    public ClientManagerImpl(ClientDaoImpl clientDao) {
        super(clientDao);
        this.mapper = new ModelMapper();
        this.clientDao = clientDao;

    }


 /*   @Override
    public ClientDTO getByName(String name) {
        return entityToDto(clientDao.getByName(name));
    }*/


    @Override
    public ClientDTO entityToDto(Object client1) {
        LOGGER.info("converting into dto");
        ClientDTO dto = mapper.map(client1, ClientDTO.class);


        return dto;
    }

    @Override
    public Client dtoToEntity(Object entity) throws Exception {
        LOGGER.info("converting into entity");
        ClientDTO dto = (ClientDTO) entity;
        Client client = mapper.map(dto, Client.class);

        checkDataInsert(dto);

        return client;
    }


}
