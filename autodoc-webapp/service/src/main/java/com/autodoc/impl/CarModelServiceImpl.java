package com.autodoc.impl;

import com.autodoc.contract.CarModelService;
import com.autodoc.model.CarModel;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CarModelServiceImpl extends GlobalServiceImpl<CarModel> implements CarModelService {
    private static Logger LOGGER = Logger.getLogger(CarModelServiceImpl.class);

    Class getObjectClass() {
        return CarModel.class;
    }


}
