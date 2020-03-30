package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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




    @Override
    public ClientDTO entityToDto(Object client1) {
        LOGGER.info("converting into dto");
        ClientDTO dto = mapper.map(client1, ClientDTO.class);
        return dto;
    }

    @Override
    public Client dtoToEntity(Object entity) throws Exception {
        LOGGER.info("converting into entity: " + entity);
        ClientDTO dto = (ClientDTO) entity;
        checkIfDuplicate(dto);
        Client client = new Client();
        client.setFirstName(dto.getFirstName().toUpperCase());
        client.setLastName(dto.getLastName().toUpperCase());
        client.setPhoneNumber(dto.getPhoneNumber().toUpperCase());

        return client;
    }


    public Client transferUpdate(Object obj) throws Exception {
        LOGGER.info("updating");
        ClientDTO dto = (ClientDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new Exception("id cannot be null");
        String firstName = dto.getFirstName().toUpperCase();
        String lastName = dto.getLastName().toUpperCase();
        String phoneNumber = dto.getPhoneNumber().toUpperCase();
        Client client = (Client) clientDao.getById(id);
        if (client == null) throw new Exception("invalid id");
        if (firstName != null) client.setFirstName(firstName.toUpperCase());
        if (lastName != null) client.setLastName(lastName.toUpperCase());
        if (phoneNumber != null) client.setPhoneNumber(phoneNumber.toUpperCase());
        checkIfDuplicate(dto);
        return client;


    }

    public void checkIfDuplicate(Object entity) throws Exception {
        LOGGER.info("checking for duplicates");
        List<Search> searchList = new ArrayList<>();
        ClientDTO dto = (ClientDTO) entity;
        Search search1 = new Search("firstName", "=", dto.getFirstName().toUpperCase());
        Search search2 = new Search("lastName", "=", dto.getLastName().toUpperCase());
        Search search3 = new Search("phoneNumber", "=", dto.getPhoneNumber().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        searchList.add(search3);
        List<Client> clients = clientDao.getByCriteria(searchList);
        LOGGER.info("clients: " + clients);
        if (!clients.isEmpty()) {
            int id = clients.get(0).getId();
            LOGGER.info("id: " + id + " / " + dto.getId());
            if (id != dto.getId())
                throw new Exception("that client already exist: " + dto.getFirstName() + " " + dto.getLastName() + " " + dto.getPhoneNumber());

        }


    }
}
