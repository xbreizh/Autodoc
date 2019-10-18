package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.client.ClientDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ClientManagerImpl<T, D> extends AbstractGenericManager implements ClientManager {
    private ClientDaoImpl clientDao;
    private Logger logger = Logger.getLogger(ClientManagerImpl.class);

    public ClientManagerImpl(ClientDaoImpl clientDao) {
        super(clientDao);
        this.clientDao = clientDao;

    }


   /* @Override
    public String save(Client client) {
        clientDao.create(client);
        return "client added";
    }

    @Override
    public List<Client> getAll() {
        logger.debug("trying to get clients");
        return clientDao.findAll();
    }

    @Override
    public String update(Client client) {
        try {
            clientDao.update(client);
            logger.info("client updated");
            return "client updated";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String delete(int clientId) {
        try {
            clientDao.deleteById(clientId);
            logger.info("client removed");
            return "client removed";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }*/

    @Override
    public ClientDTO getByName(String name) {
        return entityToDto(clientDao.getByName(name));
    }


    @Override
    public ClientDTO entityToDto(Object entity) {
        Client client = (Client) entity;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setId(client.getId());
        clientDTO.setPhoneNumber1(client.getPhoneNumber1());
        clientDTO.setPhoneNumber2(client.getPhoneNumber2());
        System.out.println("magic conversion / mapping");
        return clientDTO;
    }





 /*   @Override
    public Client getById(int id) {
        return (Client) clientDao.getById(id);
    }*/
}
