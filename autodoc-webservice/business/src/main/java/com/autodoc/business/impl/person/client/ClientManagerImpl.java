package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.search.Search;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
@Builder
public class ClientManagerImpl extends AbstractGenericManager implements ClientManager {
    private static final Logger LOGGER = Logger.getLogger(ClientManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private ClientDao dao;

    public Class getEntityClass() {
        return Client.class;
    }

    public Class getDtoClass() {
        return ClientDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    public Client transferUpdate(Object obj) {
        LOGGER.info("updating");
        ClientDTO dto = (ClientDTO) obj;
        Client client = (Client) dao.getById(dto.getId());
        checkAllRequiredValuesArePassed(dto);
        checkIfIdIsValid(dto.getId());
        checkIfDuplicate(dto);
        if (dto.getFirstName() != null) client.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) client.setLastName(dto.getLastName());
        if (dto.getPhoneNumber() != null) client.setPhoneNumber(dto.getPhoneNumber());
        LOGGER.info("client updated: " + client);
        return client;


    }

    public void checkAllRequiredValuesArePassed(ClientDTO dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty())
            throw new InvalidDtoException("You must provide a firstName");
        if (dto.getLastName() == null || dto.getLastName().isEmpty())
            throw new InvalidDtoException("You must provide a lastName");
    }


    public Client checkIfIdIsValid(int id) {
        if (id == 0) throw new InvalidDtoException("id cannot be null");
        Client client = (Client) dao.getById(id);
        if (client == null) throw new InvalidDtoException("invalid id");
        return client;
    }

    public void checkIfDuplicate(Object entity) {
        LOGGER.info("checking for duplicates");
        List<Search> searchList = new ArrayList<>();
        ClientDTO dto = (ClientDTO) entity;
        Search search1 = new Search("firstName", "=", dto.getFirstName().toUpperCase());
        Search search2 = new Search("lastName", "=", dto.getLastName().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        List<Client> clients = dao.getByCriteria(searchList);
        LOGGER.info("clients: " + clients);
        if (!clients.isEmpty()) {
            int id = clients.get(0).getId();
            LOGGER.info("id: " + id + " / " + dto.getId());
            if (id != dto.getId())
                throw new InvalidDtoException("that client already exist: " + dto.getFirstName() + " " + dto.getLastName() + " " + dto.getPhoneNumber());

        }


    }
}
