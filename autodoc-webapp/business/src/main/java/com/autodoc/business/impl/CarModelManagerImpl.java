package com.autodoc.business.impl;


import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.CarModelService;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CarModelManagerImpl extends GlobalManagerImpl<CarModel, CarModelDTO> implements CarModelManager {

    private static final Logger LOGGER = Logger.getLogger(CarModelManagerImpl.class);


    private CarModelService service;

    @Inject
    private ManufacturerManager manufacturerManager;

    public CarModelManagerImpl(CarModelService service, ManufacturerManager manufacturerManager) {
        super(service);
        this.service = service;
        this.manufacturerManager = manufacturerManager;
        LOGGER.info("manuf: " + manufacturerManager);
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
