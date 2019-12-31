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
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/providers")
public class ProviderControllerImpl extends GlobalController<ProviderDTO, Provider> implements ProviderController {

    private static Logger LOGGER = Logger.getLogger(ProviderControllerImpl.class);
    // @Inject
    ProviderManager manager;

    public ProviderControllerImpl(LibraryHelper helper, ProviderManager manager) {
        super(helper);
        this.manager = manager;
    }


    @GetMapping("")
    public ModelAndView providers() throws Exception {
        LOGGER.info("retrieving providers");
        ModelAndView mv = checkAndAddConnectedDetails("providers");
        List<Provider> providers;
        try {
           providers = manager.getAll(helper.getConnectedToken());
            LOGGER.info("providers found: " + providers.size());
            if (providers.isEmpty()) {
                return sendError(mv, "no provider found");
            }
            mv.addObject("providers", providers);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }




        return mv;

    }



    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView providerById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("providers_details");
        LOGGER.info("provider is null");
        Provider provider = (Provider) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + provider.getPhoneNumber1());
        LOGGER.info("provider: " + provider);
        mv.addObject("providerForm", provider);
        mv.addObject("showForm", 1);
        mv.addObject("provider", provider);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid ProviderForm providerForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + providerForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("providers_details");
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
        ModelAndView mv = checkAndAddConnectedDetails("providers_new");
        mv.addObject("providerForm", new ProviderForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid ProviderForm providerForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("providers_new");
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
        return providers();
        //return new ModelAndView("redirect:" + "/providers/" );
    }

    private ProviderDTO convertFormIntoDto(ProviderForm providerForm) {
        LOGGER.info("TODO");
        return null;
    }


}

