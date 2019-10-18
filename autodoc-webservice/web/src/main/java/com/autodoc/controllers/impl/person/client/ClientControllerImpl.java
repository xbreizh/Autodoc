package com.autodoc.controllers.impl.person.client;


import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.person.client.ClientController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.client.ClientDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientControllerImpl extends GlobalControllerImpl<ClientDTO, Client> implements ClientController {
    private Logger logger = Logger.getLogger(ClientControllerImpl.class);
    private ClientManager clientManager;
    private GsonConverter converter;

    public ClientControllerImpl(ClientManager clientManager) {
        super(clientManager);
        converter = new GsonConverter();
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
    public ResponseEntity getByName(@RequestBody String name) {
        ClientDTO client = clientManager.getByName(name);
        String response = converter.convertObjectIntoGsonObject(client);

        return ResponseEntity.ok(response);
    }

  /*  @Override
    @GetMapping(value = "/getById/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getClientById(@PathVariable Integer clientId) {
        Client client = (Client) clientManager.getById(clientId);
        String response = converter.convertObjectIntoGsonObject(client);

        return ResponseEntity.ok(response);
    }*/


    @Override
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Client client) {
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
    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Client client) {
        logger.debug("trying to update a client: " + client);
        String response = clientManager.update(client);
        if (response.equals("client updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}
