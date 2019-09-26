package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.impl.CarDaoImpl;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarManagerImpl implements CarManager {
    CarDaoImpl<Car> dao;
    private Logger logger = Logger.getLogger(CarManagerImpl.class);

   /* public CarManagerImpl(IGenericDao<Car> daoToSet) {
        this.dao = daoToSet;
        dao.setClazz(Car.class);
    }*/

    public CarManagerImpl(CarDaoImpl<Car> dao) {
        this.dao = dao;

    }

    //@Inject
    //IGenericDao<Car> dao;

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
