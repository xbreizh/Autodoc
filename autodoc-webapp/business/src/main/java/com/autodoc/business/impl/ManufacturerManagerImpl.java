package com.autodoc.business.impl;


import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.ManufacturerService;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

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

/*    public List<Manufacturer> convertList(String token, List<Object> list) {
        LOGGER.info("converting list: "+list);
        List<Manufacturer> newList = new ArrayList<>();
        for (Object obj : list) {
            ManufacturerDTO dto = (ManufacturerDTO) obj;
            Manufacturer manufacturer = dtoToEntity(token, obj);


            newList.add(manufacturer);
        }
        return newList;
    }*/

}
