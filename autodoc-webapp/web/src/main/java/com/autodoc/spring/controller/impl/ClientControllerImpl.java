package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.client.ClientForm;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.spring.controller.contract.ClientController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/clients")
public class ClientControllerImpl extends GlobalController implements ClientController {

    private static Logger LOGGER = Logger.getLogger(ClientControllerImpl.class);
    // @Inject
    ClientManager clientManager;

    public ClientControllerImpl(LibraryHelper helper, ClientManager clientManager) {
        super(helper);
        this.clientManager = clientManager;
    }


    @GetMapping("")
    public ModelAndView clients() throws Exception {
        LOGGER.info("retrieving clients");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients");

        List<Client> clients = getClients();

        if (clients.isEmpty()) {
            return sendError(mv, "no client found");
        }

        mv.addObject("clients", clients);
        return mv;

    }

    private List<Client> getClients() throws Exception {
        List<Client> list = (List<Client>) clientManager.getAll(helper.getConnectedToken());
        return list;
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView clientById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_details");
        LOGGER.info("client is null");
        Client client = (Client) clientManager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + client.getPhoneNumber());
        mv.addObject("clientForm", client);
        mv.addObject("showForm", 1);
        mv.addObject("client", client);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid ClientForm clientForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + clientForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_details");
        mv.addObject("clientForm", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Client client = (Client) clientManager.getById(helper.getConnectedToken(), clientForm.getId());
            mv.addObject("client", client);
            mv.addObject("clientForm", clientForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("client retrieved: " + clientForm);
        clientManager.update(helper.getConnectedToken(), clientForm);
        return new ModelAndView("redirect:" + "/clients/" + clientForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        clientManager.delete(helper.getConnectedToken(), id);
        return clients();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_new");
        mv.addObject("clientForm", new ClientForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid ClientForm clientForm, BindingResult bindingResult) {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_new");
        mv.addObject("clientForm", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("clientForm", clientForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        return new ModelAndView("redirect:/clients");
    }


}

