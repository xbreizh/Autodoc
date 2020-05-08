package com.autodoc.controllers.impl.person.client;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.controllers.contract.person.client.ClientController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.controllers.impl.exceptions.NotImplementedmethodException;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clients")
@Builder
public class ClientControllerImpl extends GlobalControllerImpl<Client, ClientDTO> implements ClientController {
    private static final Logger LOGGER = Logger.getLogger(ClientControllerImpl.class);
    private ClientManager clientManager;


    public IGenericManager getManager() {
        return clientManager;
    }

    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByName(@RequestParam(value = "name") String name) throws NotImplementedmethodException {
        LOGGER.error("trying to use a not yet implemented method");
        throw new NotImplementedmethodException(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }

}
