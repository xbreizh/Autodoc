package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Transactional
@Component
public class CarManagerImpl extends AbstractGenericManager implements CarManager {
    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);
    @Inject
    private CarDao carDao;
    private CarModelDao carModelDao;
    private ClientDao clientDao;
    // @Inject
    private ModelMapper mapper;


    public CarManagerImpl(ClientDao clientDao, CarDao dao, CarModelDao carModelDao) {
        super(dao);
        this.carModelDao = carModelDao;
        this.clientDao = clientDao;
        this.carDao = dao;
        this.mapper = new ModelMapper();
        LOGGER.info("creating manager");
    }

    /*   public CarManagerImpl() {
           this.mapper = new ModelMapper();
       }
   */
    @Override
    public CarDTO getByRegistration(String registration) {
        LOGGER.info("reg: " + registration);
        System.out.println(carDao);
        Car car = carDao.getCarByRegistration(registration.toUpperCase());
        System.out.println(car);
        if (car == null) {
            LOGGER.info("car is null");
            return null;
        }
        CarDTO carDTO = entityToDto(car);
        return carDTO;
    }


    @Override
    public CarDTO updateClient(int carId, int clientId) throws Exception {
        CarDTO carDto = (CarDTO) carDao.getById(carId);
        Client client = (Client) clientDao.getById(clientId);
        if (client == null) throw new NotFoundException("client not found");
        return null;
    }


    @Override
    public CarDTO entityToDto(Object entity) {
        LOGGER.info("converting into dto");
        if (entity == null) return null;
        System.out.println(entity);
        CarDTO dto = new CarDTO();
        System.out.println("mapper: " + mapper);
        dto = mapper.map(entity, CarDTO.class);
        return dto;
    }

    @Override
    public Car dtoToEntity(Object obj) throws Exception {
        LOGGER.info("converting into entity");
        resetException();
        CarDTO dto = (CarDTO) obj;
        String registration = dto.getRegistration();
        if (registration.isEmpty()) throw new Exception("registration cannot be empty");
        dto.setRegistration(registration.toUpperCase());
        if (checkIfExistingCar(registration)) throw new Exception("car already exist");
        Car car = mapper.map(dto, Car.class);
        checkData(dto);
        checkCarModelExist(dto.getCarModelId());
        LOGGER.info("car: " + car);
        return car;
    }

    @Override
    public Car transferUpdate(Object obj) throws Exception {
        System.out.println("transfer update");
        CarDTO dto = (CarDTO) obj;
        System.out.println("dto: " + dto);
        int id = dto.getId();
        int clientId = dto.getClientId();
        int carModelId = dto.getCarModelId();
        String registration = dto.getRegistration();
        System.out.println("registration: " + registration);
        if (clientId == 0 && carModelId == 0)
            throw new Exception("no update parameters provided");
        Car car = null;
        if (id != 0) {
            car = (Car) carDao.getById(id);
            if (car == null) throw new Exception("invalid car id: " + id);
        } else if (!registration.isEmpty()) {
            car = carDao.getCarByRegistration(registration);
            if (car == null) throw new Exception("invalid registration: " + registration);
        }

        System.out.println("car found: " + car);
        if (car == null) throw new Exception("invalid car id: " + id);

        Client client = (Client) clientDao.getById(clientId);
        if (clientId != 0) {
            if (client == null) throw new Exception("invalid client id: " + clientId);
            car.setClient(client);
            System.out.println("client set");
        }
        CarModel carModel = (CarModel) carModelDao.getById(carModelId);
        if (carModelId != 0) {
            if (carModel == null) throw new Exception("invalid carModel id: " + carModelId);
            System.out.println("getting carModel");
            car.setCarModel(carModel);
            System.out.println("carModel set");
        }

        if (registration != null && !registration.isEmpty() && id != 0) {
            Car carCheck = carDao.getCarByRegistration(registration);
            if (carCheck != null && carCheck.getId() != id)
                throw new Exception("there is another existing car with that registration");

            car.setRegistration(registration);
        }
        System.out.println("car: " + car);
        return car;
    }

    private boolean checkRegistrationNotInUse(int id, String registration) {
        LOGGER.info("checking that the registration is not used for another car");
        Car car = carDao.getCarByRegistration(registration);
        if (car == null) return false;
        return car.getId() == id;
    }

    boolean checkIfExistingCar(String registration) {
        System.out.println("checking if car already in the db");
        return carDao.getCarByRegistration(registration.toUpperCase()) != null;
    }

    private void checkData(CarDTO dto) throws Exception {
        checkRegistrationValid(dto.getRegistration());
        checkClientExist(dto.getClientId());
    }

    private boolean checkRegistrationValid(String registration) {
        // TODO
        return true;
    }

    private void checkClientExist(int clientId) throws Exception {
        if (clientDao.getById(clientId) == null) {
            throw new EntityNotFoundException("invalid clientId: " + clientId);

        }
    }

    private void checkCarModelExist(int carModelId) throws Exception {
        if (carModelDao.getById(carModelId) == null) {
            throw new EntityNotFoundException("invalid carModelId: " + carModelId);

        }
    }


}
