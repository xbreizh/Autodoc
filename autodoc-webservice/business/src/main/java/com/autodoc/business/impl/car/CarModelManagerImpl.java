package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class CarModelManagerImpl implements CarModelManager {
    private CarModelDaoImpl<CarModel> carModelDao;
    private Logger logger = Logger.getLogger(CarModelManagerImpl.class);


    public CarModelManagerImpl(CarModelDaoImpl<CarModel> carModelDao) {
        this.carModelDao = carModelDao;

    }


    @Override
    public List<CarModel> getAll() {
        return carModelDao.findAll();
    }

    @Override
    public CarModel getById(int id) {
        return (CarModel) carModelDao.findOne(id);
    }

    @Override
    public CarModel getByName(String name) {
        CarModel carModel = carModelDao.findByName(name);
        if(carModel!=null)return carModel;
        return null;
    }

}
