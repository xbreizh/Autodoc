package com.autodoc.controllers.impl;


import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.ClientController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientControllerImpl implements ClientController {
    private Logger logger = Logger.getLogger(ClientControllerImpl.class);
    private ClientManager clientManager;
    private GsonConverter converter;

    public ClientControllerImpl(ClientManager clientManager) {
        if (converter == null) converter = new GsonConverter();
        this.clientManager = clientManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {
        List<Client> list = clientManager.getAll();
        logger.debug("list: " + list);
        String response = converter.convertObjectIntoGsonObject(list);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getClientByName(@RequestBody String name) {
        Client client = clientManager.getByName(name);
        String response = converter.convertObjectIntoGsonObject(client);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity getClientById(int id) {
        return null;
    }

    @Override
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addClient(@RequestBody Client client) {
        logger.debug("trying to add a client: " + client);
        String response = clientManager.save(client);
        if (response.equals("client added")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateClient(@RequestBody Client client) {
        logger.debug("trying to update a client: " + client);
        String response = clientManager.update(client);
        System.out.println("response: "+response);
        if (response.equals("client updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }




}
