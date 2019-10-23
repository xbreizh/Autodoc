package com.autodoc.controllers.impl.person.provider;


import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.controllers.contract.person.provider.CountryController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/countries")
public class CountryControllerImpl extends GlobalControllerImpl<Country, CountryDTO> implements CountryController {
    private final static Logger LOGGER = Logger.getLogger(CountryControllerImpl.class);
    private CountryManager manager;
    private GsonConverter converter;

    public CountryControllerImpl(CountryManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


}
