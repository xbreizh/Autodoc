package com.autodoc.business.contract.person.client;

import com.autodoc.model.models.person.client.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientManager {


    String save(Client client);

    List<Client> getAll();

}