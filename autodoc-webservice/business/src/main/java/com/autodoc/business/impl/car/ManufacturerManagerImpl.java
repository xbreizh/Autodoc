package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.filler.ManufacturerFiller;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
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
    private ManufacturerFiller manufacturerFiller;


    public ManufacturerManagerImpl(ManufacturerDaoImpl<Manufacturer> manufacturerDao, ManufacturerFiller manufacturerFiller) {
        System.out.println("here");
        this.manufacturerDao = manufacturerDao;
        this.manufacturerFiller = manufacturerFiller;

    }


    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturers = manufacturerDao.findAll();
        if (manufacturers.size()==0){
            System.out.println("filling the dao");
            manufacturerFiller.fill();
            System.out.println(manufacturerDao.getByName("RENAULT").toString());
        }
        return manufacturers;
    }


}
