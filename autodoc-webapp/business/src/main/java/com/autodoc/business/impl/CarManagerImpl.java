package com.autodoc.business.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.BillService;
import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.CarForm;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.List;

@Named
public class CarManagerImpl extends GlobalManagerImpl<Car, CarDTO> implements CarManager {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);


    private CarService service;

    //@Inject
    private CarModelManager carModelManager;
    // @Inject
    private ClientManager clientManager;
    private BillService billService;

    public CarManagerImpl(CarService service, CarModelManager carModelManager, ClientManager clientManager, BillService billService) {
        super(service);
        this.service = service;
        this.billService = billService;
        this.carModelManager = carModelManager;
        this.clientManager = clientManager;
        LOGGER.info("created stuff" + clientManager);
    }

    @Override
    public Car getByRegistration(String token, String registration) throws Exception {
        LOGGER.info("trying to get car by registration");
        LOGGER.info(service);
        CarDTO dto = service.getByRegistration(token, registration);
        if (dto == null) return null;
        Car car = dtoToEntity(token, dto);
        LOGGER.info("yoyho");
        if (car == null) {
            LOGGER.info("car is null");
            return null;
        }
        return car;
    }

    public CarDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        CarForm dto = (CarForm) obj;
        LOGGER.info("dto: " + dto);
        LOGGER.info(dto.getRegistration());
        CarDTO carDTO = new CarDTO();
        if (dto.getId() != 0) carDTO.setId(dto.getId());
        carDTO.setClientId(dto.getClientId());
        carDTO.setCarModelId(dto.getCarModelId());
        carDTO.setRegistration(dto.getRegistration());
        LOGGER.info("entity transferred: " + carDTO);
        return carDTO;
    }

    public Car dtoToEntity(String token, Object obj) throws Exception {
        LOGGER.info("convert into car");
        CarDTO dto = (CarDTO) obj;
        Car car = new Car();
        car.setId(dto.getId());
        car.setRegistration(dto.getRegistration());
        car.setClient(setClient(token, dto.getClientId()));
        car.setModel(setModel(token, dto.getCarModelId()));
        car.setBills(setBills(token, dto.getRegistration()));
        LOGGER.info("car: " + car);
        return car;
    }

    private CarModel setModel(String token, int modelId) throws Exception {
        LOGGER.info("modelId: " + modelId);
        if (modelId <= 0) return null;
        CarModel model = (CarModel) carModelManager.getById(token, modelId);
        LOGGER.info("model found: " + model);
        return model;
    }

    private Client setClient(String token, int clientId) throws Exception {
        if (clientId <= 0) return null;
        LOGGER.info("clientId: " + clientId);
        LOGGER.info("clientManager " + clientManager);
        Client client = (Client) clientManager.getById(token, clientId);
        LOGGER.info("client found: " + client);
        return client;
    }

    private List<Bill> setBills(String token, String registration) throws Exception {
        LOGGER.info("trying to gather the bills");
        if (registration.isEmpty()) return null;
        SearchDto searchDto = new SearchDto();
        searchDto.setFieldName("car.registration");
        searchDto.setCompare("equals");
        searchDto.setValue(registration);
        List<Bill> bills = (List<Bill>) billService.getByCriteria(token, searchDto);
        LOGGER.info("bills found: " + bills.size());
        return bills;
    }


}
