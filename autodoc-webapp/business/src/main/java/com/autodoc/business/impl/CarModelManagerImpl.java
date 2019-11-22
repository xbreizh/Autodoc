package com.autodoc.business.impl;


import com.autodoc.business.contract.CarModelManager;
import com.autodoc.contract.CarModelService;
import com.autodoc.model.models.CarModel;
import com.autodoc.model.dtos.CarModelDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CarModelManagerImpl extends GlobalManagerImpl<CarModel, CarModelDTO> implements CarModelManager {

    private static final Logger LOGGER = Logger.getLogger(CarModelManagerImpl.class);


    private CarModelService service;

    public CarModelManagerImpl(CarModelService service) {
        super(service);
        this.service = service;
    }


}
