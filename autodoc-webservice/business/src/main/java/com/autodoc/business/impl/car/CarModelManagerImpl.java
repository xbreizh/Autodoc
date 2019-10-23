package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class CarModelManagerImpl<D, T> extends AbstractGenericManager implements CarModelManager {
    private CarModelDaoImpl carModelDao;
    private Logger logger = Logger.getLogger(CarModelManagerImpl.class);


    public CarModelManagerImpl(CarModelDaoImpl carModelDao) {
        super(carModelDao);
        this.carModelDao = carModelDao;

    }


    @Override
    public List<CarModel> getAll() {
        return carModelDao.getAll();
    }

    @Override
    public CarModelDTO entityToDto(Object entity) {
        CarModel carModel = (CarModel) entity;
       /* CarModelDTO carModelDTO = new CarModelDTO();
        return carModelDTO;*/
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }

    @Override
    public CarModel getById(int id) {
        return (CarModel) carModelDao.getById(id);
    }

    @Override
    public CarModelDTO getByName(String name) {
        CarModelDTO carModel = entityToDto(carModelDao.findByName(name));
        return carModel;
    }


}
