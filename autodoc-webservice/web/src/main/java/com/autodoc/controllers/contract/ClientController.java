package com.autodoc.controllers.contract;

import com.autodoc.model.models.person.client.Client;

public interface ClientController {


    String getAll();

    Client getClientByName(String name);

    String getClientById(int id);

    String addClient(Client client);

    String updateClient();


    String deleteClient(int clientId);
}
