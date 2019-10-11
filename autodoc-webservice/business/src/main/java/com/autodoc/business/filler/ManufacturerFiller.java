package com.autodoc.business.filler;

import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ManufacturerFiller {

    private ManufacturerDaoImpl<Manufacturer> manufacturerDao;

    public ManufacturerFiller(ManufacturerDaoImpl<Manufacturer> manufacturerDao) {
        this.manufacturerDao=manufacturerDao;
    }

    public void fill(){
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }



}
