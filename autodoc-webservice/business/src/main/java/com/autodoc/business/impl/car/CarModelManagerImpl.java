package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
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
    private ManufacturerDao manufacturerDao;

    public Class getEntityClass() {
        return CarModel.class;
    }

    public Class getDtoClass() {
        return CarModelDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }

    @Override
    public CarModelDTO getByName(String name) {
        LOGGER.info("trying to get by name: " + name);
        if (name == null || name.isEmpty()) return null;
        return (CarModelDTO) entityToDto(dao.getByName(name));
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

    public CarModel transferUpdate(Object obj) {
        LOGGER.info("transfer Update");
        CarModelDTO dto = (CarModelDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id provided");
        CarModel carModel = (CarModel) dao.getById(id);
        if (carModel == null) throw new InvalidDtoException("invalid id " + id);
        if (dto.getName() != null) carModel.setName(dto.getName());
        if (dto.getDescription() != null) carModel.setDescription(dto.getDescription());
        if (dto.getEngine() != null) carModel.setEngine(dto.getEngine());
        passingGearBox(dto, carModel);
        passingFuelType(dto, carModel);
        if (dto.getManufacturerId() != 0) {
            Manufacturer manufacturer = (Manufacturer) manufacturerDao.getById(id);
            if (manufacturer == null)
                throw new InvalidDtoException("invalid manufacturer if: " + dto.getManufacturerId());
            carModel.setManufacturer(manufacturer);
        }
        return carModel;
    }

    private void passingFuelType(CarModelDTO dto, CarModel carModel) {
        try {
            if (dto.getFuelType() != null) carModel.setFuelType(FuelType.valueOf(dto.getFuelType()));
        } catch (Exception e) {
            LOGGER.error("invalid fueltype");
            throw new InvalidDtoException("invalid fuelType: " + dto.getFuelType().toUpperCase());
        }
    }

    private void passingGearBox(CarModelDTO dto, CarModel carModel) {
        try {
            if (dto.getGearbox() != null) carModel.setGearbox(GearBox.valueOf(dto.getGearbox()));
        } catch (Exception e) {
            LOGGER.error("invalid gearbox");
            throw new InvalidDtoException("invalid gearbox: " + dto.getGearbox().toUpperCase());
        }
    }


}
