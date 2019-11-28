package com.autodoc.impl;

import com.autodoc.contract.CarModelService;
import com.autodoc.model.dtos.car.CarModelDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CarModelServiceImpl extends GlobalServiceImpl<CarModelDTO> implements CarModelService {
    private static Logger LOGGER = Logger.getLogger(CarModelServiceImpl.class);

    Class getObjectClass() {
        return CarModelDTO.class;
    }


    public String getClassName() {
        return "carModels";
    }


}
