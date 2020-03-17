package com.autodoc.impl;

import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ProviderServiceImpl extends GlobalServiceImpl<ProviderDTO> implements ProviderService {
    private static Logger LOGGER = Logger.getLogger(ProviderServiceImpl.class);

    Class getObjectClass() {
        return ProviderDTO.class;
    }
    Class getListClass() {
        return ProviderDTO[].class;
    }

    public String getClassName() {
        return "providers";
    }


}

