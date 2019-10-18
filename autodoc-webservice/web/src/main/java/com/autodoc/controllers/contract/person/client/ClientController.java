package com.autodoc.controllers.contract.person.client;

import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.model.models.person.client.Client;
import org.springframework.http.ResponseEntity;

public interface ClientController extends GlobalController {


    //  ResponseEntity getAll();

    ResponseEntity getByName(String name);

    //  ResponseEntity getById(Integer id);

    ResponseEntity add(Client client);

    ResponseEntity update(Client client);


}
