package com.autodoc.business.impl;


import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.CarModelService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
@Builder
public class CarModelManagerImpl extends GlobalManagerImpl<CarModel, CarModelDTO> implements CarModelManager {

    private static final Logger LOGGER = Logger.getLogger(CarModelManagerImpl.class);


    CarModelService service;
    private ManufacturerManager manufacturerManager;

    GlobalService getService() {
        return service;
    }

    public CarModel dtoToEntity(String token, Object obj) throws Exception {
        LOGGER.info("converting into entity");
        CarModelDTO dto = (CarModelDTO) obj;
        CarModel carModel = new CarModel();
        carModel.setId(dto.getId());
        carModel.setName(dto.getName());
        carModel.setDescription(dto.getDescription());
        carModel.setFuelType(dto.getFuelType());
        carModel.setEngine(dto.getEngine());
        carModel.setGearbox(dto.getGearbox());
        carModel.setManufacturer(setManufacturer(token, dto.getManufacturerId()));
        return carModel;
    }

    private Manufacturer setManufacturer(String token, int manufacturerId) throws Exception {
        LOGGER.info("manufacturer id: " + manufacturerId);
        Manufacturer manufacturer = (Manufacturer) manufacturerManager.getById(token, manufacturerId);
        LOGGER.info("manufacturer: " + manufacturer);
        return manufacturer;
    }


}
