package com.autodoc.controllers.contract.person.client;

import org.springframework.http.ResponseEntity;

public interface ClientController {


    ResponseEntity getByName(String name);


}
