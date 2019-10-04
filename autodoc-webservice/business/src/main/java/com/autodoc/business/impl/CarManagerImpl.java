package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.impl.CarDaoImpl;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class CarManagerImpl implements CarManager {
    private CarDaoImpl<Car> carDao;
    private Logger logger = Logger.getLogger(CarManagerImpl.class);

   /* public CarManagerImpl(IGenericDao<Car> daoToSet) {
        this.dao = daoToSet;
        dao.setClazz(Car.class);
    }*/

    public CarManagerImpl(CarDaoImpl<Car> dao) {
        this.carDao = dao;

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
        logger.debug("trying to save a car");
        logger.info("trying to save a like: "+car);
        //car.setRegistration("morning");
        carDao.create(car);
        return true;
    }

    @Override
    public List<Car> getAll() {
        return carDao.findAll();
    }

    @Override
    public Car getByRegistration(String registration) {
        return null;
        //return carDao.getCarByRegistration(registration);
    }
}
