package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.mock;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbClient.sql")
@Transactional
class ClientManagerImplTest {

    ClientDao clientDao;

    ClientManager clientManager;

    Client client;
    ClientDTO dto;

    @BeforeEach
    void init() {
        //clientDao= new ClientDaoImpl<>();
        clientDao = mock(ClientDaoImpl.class);
        clientManager = mock(ClientManager.class);
        clientDao = mock(ClientDao.class);
        // System.out.println(clientDao);
        //System.out.println(clientDao);
        //clientManager = new ClientManagerImpl<Client, ClientDTO>(clientDao);

        client = new Client();
    }


    @Test
    @Disabled
    void entityToDto() {

        clientManager.entityToDto(client);
    }


    @Test
    @Disabled
    void dtoToEntity(Object entity) {

    }
}