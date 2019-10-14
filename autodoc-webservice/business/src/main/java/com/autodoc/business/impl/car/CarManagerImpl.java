package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
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
    public String save(Car car) {
        logger.debug("trying to save a car");
        logger.info("trying to save a like: " + car);
        //car.setRegistration("morning");
        try {
            carDao.create(car);
            return "car added";
        } catch (ConstraintViolationException e) {
            logger.debug("error: " + e.getLocalizedMessage());
            return e.getMessage();
        }

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

    @Override
    public String update(Car car) {
        try {
            carDao.update(car);
            return "car added";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
