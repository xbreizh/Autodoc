package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.contract.IGenericDao;
import com.autodoc.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarManagerImpl implements CarManager {

    @Autowired
    IGenericDao<Car> dao;

    @Autowired
    public void setDao(IGenericDao<Car> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Car.class);
    }


    @Override
    public boolean save(Car car) {

        dao.deleteById(16);
        dao.deleteById(17);
        dao.deleteById(18);
        return true;
    }
}
