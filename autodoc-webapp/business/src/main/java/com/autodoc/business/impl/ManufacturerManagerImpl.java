package com.autodoc.business.impl;


import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.ManufacturerService;
import com.autodoc.model.Manufacturer;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ManufacturerManagerImpl extends GlobalManagerImpl<Manufacturer> implements ManufacturerManager {

    private static final Logger LOGGER = Logger.getLogger(ManufacturerManagerImpl.class);


    private ManufacturerService service;

    public ManufacturerManagerImpl(ManufacturerService service) {
        super(service);
        this.service = service;
        System.out.println("created stuff" + service);
    }

}
