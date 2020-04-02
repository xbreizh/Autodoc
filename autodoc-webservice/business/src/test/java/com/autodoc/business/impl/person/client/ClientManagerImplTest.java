/*
package com.autodoc.business.impl.person.client;


import com.autodoc.business.contract.person.client.ClientManager;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;*/
/*

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class ClientManagerImplTest {

    ClientDaoImpl clientDao;

    ClientManager clientManager;

    Client client;
    ClientDTO dto;

    @BeforeEach
    void init() {
        clientDao = mock(ClientDaoImpl.class);
        clientManager = new ClientManagerImpl(clientDao);
        // clientDao = mock(ClientDao.class);
        client = new Client();
        dto = new ClientDTO();
    }


    @Test
    @Disabled
    void entityToDto() {

        clientManager.entityToDto(client);
    }


    @Test
    void save() throws Exception {
        int id = 9;
        dto.setFirstName("mo");
        dto.setLastName("ma");
        dto.setPhoneNumber("yok");
        when(clientDao.create(any(Client.class))).thenReturn(id);
        assertEquals(Integer.toString(id), clientManager.save(dto));
    }


    @Test
    @Disabled
    void dtoToEntity(Object entity) {

    }

}*/