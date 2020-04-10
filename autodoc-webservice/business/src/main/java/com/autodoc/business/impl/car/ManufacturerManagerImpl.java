package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class ManufacturerManagerImpl extends AbstractGenericManager implements ManufacturerManager {
    private static final ModelMapper mapper = new ModelMapper();
    private static final Logger LOGGER = Logger.getLogger(ManufacturerManagerImpl.class);
    private ManufacturerDao dao;

    public Class getEntityClass() {
        return Manufacturer.class;
    }

    public Class getDtoClass() {
        return ManufacturerDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }

    @Override
    public ManufacturerDTO getByName(String name) {
        LOGGER.info("trying to get: " + name);
        if (name.isEmpty()) return null;
        return (ManufacturerDTO) entityToDto(dao.getByName(name));
    }


    @Override
    public void checkIfDuplicate(Object dtoToCheck) {
        ManufacturerDTO dto = (ManufacturerDTO) dtoToCheck;
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDtoException("there should be a name");
        }
        if (dao.getByName(dto.getName()) != null) {
            throw new InvalidDtoException("Manufacturer already exist with that name");
        }
        LOGGER.info("all good");
    }

}
