package com.autodoc.business.contract.person.client;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.person.client.ClientDTO;
import org.springframework.stereotype.Service;

@Service
public interface ClientManager extends IGenericManager {


  /*  String save(Client client);

    List<ClientDTO> getAll();

    String update(Client any);

    String delete(int anyInt);*/

    ClientDTO getByName(String name);


    /* Client getById(int id);*/
}
