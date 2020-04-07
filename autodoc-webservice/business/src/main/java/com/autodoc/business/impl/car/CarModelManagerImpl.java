package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class CarModelManagerImpl extends AbstractGenericManager implements CarModelManager {
    private static final Logger LOGGER = Logger.getLogger(CarModelManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private CarModelDao dao;


    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao");
        return dao;
    }


    @Override
    public CarModelDTO entityToDto(Object entity) {
        return mapper.map(entity, CarModelDTO.class);
    }

    @Override
    public CarModel dtoToEntity(Object entity) {
        if (entity == null) return null;
        checkIfDuplicate(entity);
        return mapper.map(entity, CarModel.class);
    }

    @Override
    public CarModelDTO getByName(String name) {
        LOGGER.info("trying to get by name: " + name);
        if (name == null || name.isEmpty()) return null;
        return entityToDto(dao.getByName(name));
    }

    @Override
    public void checkIfDuplicate(Object dtoToCheck) {
        CarModelDTO dto = (CarModelDTO) dtoToCheck;
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDtoException("there should be a name");
        }
        if (dao.getByName(dto.getName()) != null) {
            throw new InvalidDtoException("CarModel already exist with that name");
        }
        LOGGER.info("all good");
    }


}
