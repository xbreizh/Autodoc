package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class ManufacturerManagerImpl implements ManufacturerManager {
    private ManufacturerDaoImpl manufacturerDao;
    private Logger logger = Logger.getLogger(ManufacturerManagerImpl.class);


    public ManufacturerManagerImpl(ManufacturerDaoImpl manufacturerDao) {
        logger.debug("here");
        this.manufacturerDao = manufacturerDao;

    }


    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        logger.info("trying to get maufacturers: " + manufacturers);
        return manufacturers;
    }

    @Override
    public Manufacturer getByName(String name) {
        logger.debug("trying to get: " + name);
        if (name.isEmpty()) return null;
        Manufacturer manufacturer = manufacturerDao.getByName(name);
        return manufacturer;
    }


}
