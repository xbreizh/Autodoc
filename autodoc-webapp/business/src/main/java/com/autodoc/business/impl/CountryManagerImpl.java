package com.autodoc.business.impl;


import com.autodoc.business.contract.CountryManager;
import com.autodoc.contract.CountryService;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CountryManagerImpl extends GlobalManagerImpl<Country, CountryDTO> implements CountryManager {

    private static final Logger LOGGER = Logger.getLogger(CountryManagerImpl.class);


    private CountryService service;

    public CountryManagerImpl(CountryService service) {
        super(service);
        this.service = service;
        System.out.println("created stuff " + service);
    }

    public Country dtoToEntity(String token, Object obj) {
        LOGGER.info("converting into entito");
        CountryDTO dto = (CountryDTO) obj;
        Country country = new Country();
        country.setName(dto.getName());
        return country;
    }

}
