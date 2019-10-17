package com.autodoc.controllers.contract.person.client;

import com.autodoc.model.models.person.client.Client;
import org.springframework.http.ResponseEntity;

public interface ClientController {


    ResponseEntity getAll();

    ResponseEntity getClientByName(String name);

    ResponseEntity getClientById(Integer id);

    ResponseEntity addClient(Client client);

    ResponseEntity updateClient(Client client);


}
