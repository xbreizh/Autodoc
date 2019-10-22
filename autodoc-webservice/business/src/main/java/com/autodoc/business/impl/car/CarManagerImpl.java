package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarManagerImpl extends AbstractGenericManager implements CarManager {
    private Logger logger = Logger.getLogger(CarManagerImpl.class);
    private CarDaoImpl carDao;
    private IGenericDao<CarModel> carModelDao;
    private IGenericDao<Client> clientDao;

    public CarManagerImpl(ClientDao clientDao, CarDaoImpl dao, CarModelDao carModelDao) {
        super(dao);
        this.clientDao = clientDao;
        this.carModelDao = carModelDao;
        this.carDao = dao;
        logger.info("creating manager");
    }


    @Override
    public CarDTO getByRegistration(String registration) {
        logger.info("reg: " + registration);
        CarDTO carDTO = entityToDto(carDao.getCarByRegistration(registration));
        return carDTO;
    }


    @Override
    public String updateClient(int carId, int clientId) {
        Car car = (Car) carDao.getById(carId);
        Client client = (Client) clientDao.getById(clientId);
        if (client == null) return "no client found";
        if (car == null) return "no car found";
        car.setClient(client);
        return update(car);
    }


    @Override
    public CarDTO entityToDto(Object car1) {
        CarDTO carDTO = new CarDTO("abc123", 2, 4);
        Car car = (Car) car1;
        carDTO.setId(car.getId());
        carDTO.setRegistration(car.getRegistration());
        carDTO.setCarModelId(car.getCarModel().getId());
        carDTO.setClientId(car.getClient().getId());
        System.out.println("magic conversion / mapping");
        return carDTO;
    }

    @Override
    public Car dtoToEntity(Object entity) {
        CarDTO dto = (CarDTO) entity;
        System.out.println("dto: " + dto);
        Car car = new Car();
        Client client = (Client) clientDao.getById(dto.getClientId());
        CarModel carModel = (CarModel) carModelDao.getById(dto.getCarModelId());
        System.out.println("client: " + client);
        System.out.println("carModel: " + carModel);
        car.setClient(client);
        car.setCarModel(carModel);
        car.setRegistration(dto.getRegistration());
        logger.info("car: " + car);
        return car;
    }


}
