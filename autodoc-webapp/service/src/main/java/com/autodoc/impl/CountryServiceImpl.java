package com.autodoc.impl;

import com.autodoc.contract.CountryService;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CountryServiceImpl extends GlobalServiceImpl<CountryDTO> implements CountryService {
    private static Logger LOGGER = Logger.getLogger(CountryServiceImpl.class);

    Class getObjectClass() {
        return CountryDTO.class;
    }
    Class getListClass() {
        return CountryDTO[].class;
    }

    public String getClassName() {
        return "countries";
    }
}
