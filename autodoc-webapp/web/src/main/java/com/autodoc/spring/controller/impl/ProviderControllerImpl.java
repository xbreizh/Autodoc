package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.ProviderManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.dtos.person.provider.ProviderForm;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.spring.controller.contract.ProviderController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/providers")
public class ProviderControllerImpl extends GlobalController<ProviderDTO, Provider, ProviderForm> implements ProviderController {

    private static Logger LOGGER = Logger.getLogger(ProviderControllerImpl.class);
    // @Inject
    private static final String KEY_WORD = "providers";
    //  ProviderManager manager;

    public ProviderControllerImpl(LibraryHelper helper, ProviderManager manager) {
        super(helper);
        this.manager = manager;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }


    @GetMapping("")
    public ModelAndView providers() throws Exception {
        return getList();

    }



    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView providerById(@PathVariable Integer id) throws Exception {
        return getById(id);
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid ProviderForm providerForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + providerForm.getId());
        LOGGER.info("phone: " + providerForm.getPhoneNumber());
        ModelAndView mv = checkAndAddConnectedDetails("providers/providers_details");
        mv.addObject("providerForm", new ProviderForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Provider provider = (Provider) manager.getById(helper.getConnectedToken(), providerForm.getId());
            mv.addObject("provider", provider);
            mv.addObject("providerForm", providerForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("provider retrieved: " + providerForm);
        manager.update(helper.getConnectedToken(), providerForm);
        return new ModelAndView("redirect:" + "/providers/" + providerForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return providers();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("providers/providers_new");
        mv.addObject("providerForm", new ProviderForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid ProviderForm providerForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("providers/providers_new");
        LOGGER.info("empl: " + providerForm);
        mv.addObject("providerForm", new ProviderForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("providerForm", providerForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("provider retrieved: " + providerForm);
        manager.add(helper.getConnectedToken(), providerForm);
        return new ModelAndView("redirect:/providers");
    }



}

