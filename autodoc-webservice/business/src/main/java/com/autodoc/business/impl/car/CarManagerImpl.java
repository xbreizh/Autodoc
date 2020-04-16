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
    public CarDTO updateClient(int carId, int clientId) {
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
    public Car dtoToEntity(Object obj) {
        LOGGER.info("converting into entity");
  /*      resetException();*/
        CarDTO dto = (CarDTO) obj;

        return mapper.map(dto, Car.class);
    }

    @Override
    public Car transferInsert(Object obj) {
        CarDTO dto = (CarDTO) obj;
        checkIfExistingCar(dto.getRegistration());
        checkClientExist(dto.getClientId());
        checkCarModelExist(dto.getCarModelId());
        return dtoToEntity(dto);
    }

    @Override
    public Car transferUpdate(Object obj) {
        LOGGER.info("transfer update");
        CarDTO dto = (CarDTO) obj;
        int id = dto.getId();
        String registration = dto.getRegistration();
        checkIfCarInDb(dto);
        Car car = dtoToEntity(dto);

        int clientId = dto.getClientId();
        int carModelId = dto.getCarModelId();
        if (clientId == 0 && carModelId == 0) {

            throw new InvalidDtoException("no update parameters provided");
        }
        LOGGER.info("dto: " + dto);
        if (carModelId != 0) {
            checkCarModelExist(carModelId);
            CarModel carModel = (CarModel) carModelDao.getById(carModelId);
            car.setCarModel(carModel);
        }
        if (clientId != 0) {
            checkClientExist(clientId);
            Client client = (Client) clientDao.getById(clientId);
            car.setClient(client);
        }

        LOGGER.info("registration: " + registration);

        LOGGER.info("car: " + car);
        return car;
    }

    public void checkIfCarInDb(CarDTO dto) {
        int id = dto.getId();
        String registration = dto.getRegistration();
        LOGGER.info("checking if car is in DB");
        if (id == 0 && (registration == null || registration.isEmpty()))
            throw new InvalidDtoException("you need to pass a registration or a car id");
        if (id != 0) {
            Car carFromDb = (Car) dao.getById(id);
            if (carFromDb == null) throw new InvalidDtoException("invalid car id: " + id);
            if (registration!=null && !registration.isEmpty() && registration!=carFromDb.getRegistration())
                throw new InvalidDtoException("invalid car registration: " +registration+". There is a different registration in db for that Id: "+id);
        } else {
            LOGGER.info("checking registration is not already in use");
            Car carFromDb = dao.getCarByRegistration(registration);
            LOGGER.info("carFrom DB: "+carFromDb);
            if (carFromDb == null)
                throw new InvalidDtoException("invalid registration: " + registration);
            dto.setId(carFromDb.getId());
        }
    }

    @Override
    public boolean checkRegistrationNotInUse(int id, String registration) {
        LOGGER.info("checking that the registration is not used for another car");
        Car car = dao.getCarByRegistration(registration);
        if (car == null) return false;
        return car.getId() == id;
    }

    @Override
    public void checkIfExistingCar(String registration) {
        LOGGER.info("checking if car already in the db");
        if (dao.getCarByRegistration(registration.toUpperCase()) != null)
            throw new InvalidDtoException("car already exist");
    }


    public void checkClientExist(int clientId) {
        if (clientDao.getById(clientId) == null) {
            throw new EntityNotFoundException("invalid clientId: " + clientId);

        }
    }

    public void checkCarModelExist(int carModelId) {
        if (carModelDao.getById(carModelId) == null) {
            throw new EntityNotFoundException("invalid carModelId: " + carModelId);

        }
    }


}
