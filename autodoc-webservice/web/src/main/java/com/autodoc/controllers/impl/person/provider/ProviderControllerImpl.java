package com.autodoc.controllers.impl.person.provider;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.controllers.contract.person.provider.ProviderController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.provider.Provider;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/providers")
@Builder
public class ProviderControllerImpl extends GlobalControllerImpl<Provider, ProviderDTO> implements ProviderController {
    private final static Logger LOGGER = Logger.getLogger(ProviderControllerImpl.class);
    private ProviderManager manager;


    public IGenericManager getManager() {
        return manager;
    }

}
