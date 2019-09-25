package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.contract.IGenericDao;
import com.autodoc.model.models.car.Car;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional
@Component
public class CarManagerImpl implements CarManager {

    public CarManagerImpl(IGenericDao<Car> daoToSet) {
        this.dao = daoToSet;
        dao.setClazz(Car.class);
    }

    //@Inject
    IGenericDao<Car> dao;

   /* @Inject
    public void setDao(IGenericDao<Car> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Car.class);
    }*/


    @Override
    public boolean save(Car car) {
        car.setRegistration("morning");
        dao.create(car);
        return true;
    }
}
