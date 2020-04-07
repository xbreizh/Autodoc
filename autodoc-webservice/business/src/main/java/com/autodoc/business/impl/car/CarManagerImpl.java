package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Component
@Builder
public class CarManagerImpl extends AbstractGenericManager implements CarManager {
    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private final CarDao dao;
    private final CarModelDao carModelDao;
    private final ClientDao clientDao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao");
        return dao;
    }



    @Override
    public CarDTO getByRegistration(String registration) {
        LOGGER.info("reg: " + registration);
        Car car = dao.getCarByRegistration(registration.toUpperCase());
        LOGGER.info(car);
        if (car == null) {
            LOGGER.info("car is null");
            return null;
        }
        return entityToDto(car);
    }


    @Override
    public CarDTO updateClient(int carId, int clientId) throws InvalidDtoException {
        Client client = (Client) clientDao.getById(clientId);
        if (client == null) throw new InvalidDtoException("client not found");
        Car car = (Car) dao.getById(carId);
        car.setClient(client);
        dao.update(car);
        return entityToDto(mapper.map(dao.getById(carId), CarDTO.class));
    }


    @Override
    public CarDTO entityToDto(Object entity) {
        LOGGER.info("converting into dto");
        if (entity == null) return null;
        LOGGER.info(entity);
        LOGGER.info("mapper: " + mapper);
        return mapper.map(entity, CarDTO.class);
    }

    @Override
    public Car dtoToEntity(Object obj) throws InvalidDtoException {
        LOGGER.info("converting into entity");
        resetException();
        CarDTO dto = (CarDTO) obj;
        String registration = dto.getRegistration();
        if (registration.isEmpty()) throw new InvalidDtoException("registration cannot be empty");
        dto.setRegistration(registration.toUpperCase());
        if (checkIfExistingCar(registration)) throw new InvalidDtoException("car already exist");
        Car car = mapper.map(dto, Car.class);
        checkData(dto);
        checkCarModelExist(dto.getCarModelId());
        LOGGER.info("car: " + car);
        return car;
    }

    @Override
    public Car transferUpdate(Object obj) throws InvalidDtoException {
        LOGGER.info("transfer update");
        CarDTO dto = (CarDTO) obj;
        LOGGER.info("dto: " + dto);
        int id = dto.getId();
        int clientId = dto.getClientId();
        int carModelId = dto.getCarModelId();
        String registration = dto.getRegistration();
        LOGGER.info("registration: " + registration);
        if (clientId == 0 && carModelId == 0)
            throw new InvalidDtoException("no update parameters provided");
        Car car = null;
        if (id != 0) {
            car = (Car) dao.getById(id);
            if (car == null) throw new InvalidDtoException("invalid car id: " + id);
        } else if (!registration.isEmpty()) {
            car = dao.getCarByRegistration(registration);
            if (car == null) throw new InvalidDtoException("invalid registration: " + registration);
        }

        LOGGER.info("car found: " + car);
        if (car == null) throw new InvalidDtoException("invalid car id: " + id);

        Client client = (Client) clientDao.getById(clientId);
        if (clientId != 0) {
            if (client == null) throw new InvalidDtoException("invalid client id: " + clientId);
            car.setClient(client);
            LOGGER.info("client set");
        }
        CarModel carModel = (CarModel) carModelDao.getById(carModelId);
        if (carModelId != 0) {
            if (carModel == null) throw new InvalidDtoException("invalid carModel id: " + carModelId);
            LOGGER.info("getting carModel");
            car.setCarModel(carModel);
            LOGGER.info("carModel set");
        }

        if (registration != null && !registration.isEmpty() && id != 0) {
            Car carCheck = dao.getCarByRegistration(registration);
            if (carCheck != null && carCheck.getId() != id)
                throw new InvalidDtoException("there is another existing car with that registration");

            car.setRegistration(registration);
        }
        LOGGER.info("car: " + car);
        return car;
    }

    private boolean checkRegistrationNotInUse(int id, String registration) {
        LOGGER.info("checking that the registration is not used for another car");
        Car car = dao.getCarByRegistration(registration);
        if (car == null) return false;
        return car.getId() == id;
    }

    boolean checkIfExistingCar(String registration) {
        LOGGER.info("checking if car already in the db");
        return dao.getCarByRegistration(registration.toUpperCase()) != null;
    }

    private void checkData(CarDTO dto) throws InvalidDtoException {
        checkRegistrationValid(dto.getRegistration());
        checkClientExist(dto.getClientId());
    }

    private boolean checkRegistrationValid(String registration) {
        // TODO
        return true;
    }

    private void checkClientExist(int clientId) throws InvalidDtoException {
        if (clientDao.getById(clientId) == null) {
            throw new EntityNotFoundException("invalid clientId: " + clientId);

        }
    }

    private void checkCarModelExist(int carModelId) throws InvalidDtoException {
        if (carModelDao.getById(carModelId) == null) {
            throw new EntityNotFoundException("invalid carModelId: " + carModelId);

        }
    }


}
