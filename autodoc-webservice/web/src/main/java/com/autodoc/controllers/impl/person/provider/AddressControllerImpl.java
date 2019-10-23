package com.autodoc.controllers.impl.person.provider;


import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.controllers.contract.person.provider.AddressController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.provider.AddressDTO;
import com.autodoc.model.models.person.provider.Address;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addresses")
public class AddressControllerImpl extends GlobalControllerImpl<Address, AddressDTO> implements AddressController {
    private final static Logger LOGGER = Logger.getLogger(AddressControllerImpl.class);
    private AddressManager manager;
    private GsonConverter converter;

    public AddressControllerImpl(AddressManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


}
