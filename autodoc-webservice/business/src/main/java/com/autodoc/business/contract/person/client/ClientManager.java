package com.autodoc.business.contract.person.client;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.client.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientManager extends IGenericManager {


  /*  String save(Client client);

    List<Client> getAll();

    String update(Client any);

    String delete(int anyInt);*/

    Client getByName(String name);

    /* Client getById(int id);*/
}
