package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.dao.impl.car.CarDaoImpl;
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


    public CarManagerImpl(CarDaoImpl<Car> dao) {
        this.carDao = dao;

    }


    @Override
    public boolean save(Car car) {
        logger.debug("trying to save a car");
        logger.info("trying to save a like: " + car);
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
