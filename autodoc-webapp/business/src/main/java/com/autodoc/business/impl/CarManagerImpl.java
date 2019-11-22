package com.autodoc.business.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CarManagerImpl extends GlobalManagerImpl<Car, CarDTO> implements CarManager {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);


    private CarService service;

    @Inject
    private CarModelManager carModelManager;
    @Inject
    private ClientManager clientManager;

    public CarManagerImpl(CarService service) {
        super(service);
        this.service = service;
        System.out.println("created stuff" + service);
    }

    @Override
    public Car getByRegistration(String token, String registration) {
        LOGGER.info("trying to get car by registration");
        System.out.println(service);
        Car car = dtoToEntity(token, service.getByRegistration(token, registration));
        if(car==null)return null;
       // CarModel carModel = carModelManager.getById(token, car.)
        System.out.println(car.getClient().getLastName());
        return car;
    }

    public Car dtoToEntity(String token, CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setRegistration(dto.getRegistration());
        car.setClient(setClient(token, dto.getClientId()));
        return car;
    }

    private Client setClient(String token, int clientId) {
        if (clientId<=0)return null;
        LOGGER.info("clientId: "+clientId);
        Client client = (Client) clientManager.getById(token, clientId);
        LOGGER.info("client found: "+client);
        return client;
    }


}
