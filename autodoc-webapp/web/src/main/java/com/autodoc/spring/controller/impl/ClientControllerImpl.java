package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.helper.contract.AuthenticationHelper;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.client.ClientForm;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.spring.controller.contract.ClientController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/clients")
public class ClientControllerImpl extends GlobalController<Client, ClientDTO, ClientForm> implements ClientController {

    private static final Logger LOGGER = Logger.getLogger(ClientControllerImpl.class);
    private static final String KEY_WORD = "clients";

    public ClientControllerImpl(AuthenticationHelper authenticationHelper, ClientManager manager) {
        super(authenticationHelper);
        this.manager = manager;
    }


    @Override
    String getKeyWord() {
        return KEY_WORD;
    }


    @GetMapping("")
    public ModelAndView allClients() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView clientById(@PathVariable Integer id) throws Exception {
        return getById(id);
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid ClientForm form, BindingResult bindingResult) throws Exception {


        if (form == null) form = new ClientForm();
        ModelAndView mv = updateObject(form, form.getId(), bindingResult);
        return mv;
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(authenticationHelper.getConnectedToken(), id);
        return new ModelAndView("redirect:" + "/clients");
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
    public ModelAndView create(@Valid ClientForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_new");
        if (form == null) form = new ClientForm();
        mv.addObject("form", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            addingErrorsToView(bindingResult, mv);
            mv.addObject("form", form);
            mv.addObject("showForm", 1);
            return mv;
        }
        String feedback = manager.add(authenticationHelper.getConnectedToken(), form);
        try {
            int id = Integer.parseInt(feedback);
            return new ModelAndView("redirect:" + "/" + KEY_WORD + "/" + id);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            mv.addObject("error", feedback);
        }
        return mv;
    }


}

