package com.autodoc.controllers.impl.person.client;


import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.person.client.ClientController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @Override
    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestBody String name) {
        ClientDTO client = clientManager.getByName(name);
        String response = converter.convertObjectIntoGsonObject(client);

        return ResponseEntity.ok(response);
    }


}
