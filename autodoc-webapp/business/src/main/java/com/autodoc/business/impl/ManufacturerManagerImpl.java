package com.autodoc.business.impl;


import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.ManufacturerService;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ManufacturerManagerImpl extends GlobalManagerImpl<Manufacturer, ManufacturerDTO> implements ManufacturerManager {

    private static final Logger LOGGER = Logger.getLogger(ManufacturerManagerImpl.class);


    private ManufacturerService service;

    public ManufacturerManagerImpl(ManufacturerService service) {
        super(service);
        this.service = service;
        System.out.println("created stuff " + service);
    }

    public Manufacturer dtoToEntity(String token, Object obj) {
        LOGGER.info("converting into entito");
        ManufacturerDTO dto = (ManufacturerDTO) obj;
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(dto.getIdentifier());
        manufacturer.setName(dto.getName());
        return manufacturer;
    }

}
