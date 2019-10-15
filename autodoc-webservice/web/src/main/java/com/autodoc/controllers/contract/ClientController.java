package com.autodoc.controllers.contract;

import com.autodoc.model.models.person.client.Client;
import org.springframework.http.ResponseEntity;

public interface ClientController {


    ResponseEntity getAll();

    Client getClientByName(String name);

    ResponseEntity getClientById(int id);

    ResponseEntity addClient(Client client);

    ResponseEntity updateClient(Client client);


    ResponseEntity deleteClient(int i);
}
