package com.autodoc.business.impl;

import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.dao.impl.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class ManufacturerManagerImpl implements ManufacturerManager {
    private ManufacturerDaoImpl<Manufacturer> manufacturerDao;
    private Logger logger = Logger.getLogger(ManufacturerManagerImpl.class);


    public ManufacturerManagerImpl(ManufacturerDaoImpl<Manufacturer> manufacturerDao) {
        System.out.println("here");
        this.manufacturerDao = manufacturerDao;

    }


    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.findAll();
    }

}
