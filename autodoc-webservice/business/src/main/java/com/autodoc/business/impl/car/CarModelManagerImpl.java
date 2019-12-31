package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarModelManagerImpl<D, T> extends AbstractGenericManager implements CarModelManager {
    private final static Logger LOGGER = Logger.getLogger(CarModelManagerImpl.class);
    private CarModelDao carModelDao;
    private ModelMapper mapper;

    public CarModelManagerImpl(CarModelDao carModelDao) {
        super(carModelDao);
        this.mapper = new ModelMapper();
        this.carModelDao = carModelDao;

    }


    @Override
    public CarModelDTO entityToDto(Object entity) {
        CarModelDTO dto = mapper.map(entity, CarModelDTO.class);
        return dto;
    }

    @Override
    public CarModel dtoToEntity(Object entity) throws Exception {
        CarModelDTO dto = (CarModelDTO) entity;
        CarModel carModel = mapper.map(entity, CarModel.class);
        checkDataInsert(dto);
        return carModel;
    }

    @Override
    public CarModelDTO getByName(String name) {
        LOGGER.info("trying to get by name: " + name);
        CarModel carModel = (CarModel) carModelDao.getByName(name);
        if (carModel == null) return null;
        return entityToDto(carModel);
    }


}
