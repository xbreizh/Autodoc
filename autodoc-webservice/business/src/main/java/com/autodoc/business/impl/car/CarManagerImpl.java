package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
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
    private CarModelManager carModelManager;
    private ClientManager clientManager;
    // @Inject
    private ModelMapper mapper;


    public CarManagerImpl(ClientManager clientManager, CarDao dao, CarModelManager carModelManager) {
        super(dao);
        this.carModelManager = carModelManager;
        this.clientManager = clientManager;
        this.carDao = dao;
        this.mapper = new ModelMapper();
        LOGGER.info("creating manager");
    }

    public CarManagerImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public CarDTO getByRegistration(String registration) {
        LOGGER.info("reg: " + registration);
        System.out.println(carDao);
        Car car = carDao.getCarByRegistration(registration);
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
        Client client = (Client) clientManager.getById(clientId);
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
        Car car = mapper.map(dto, Car.class);
        checkData(dto);
        checkCarModelExist(dto.getCarModelId());
        LOGGER.info("car: " + car);
        return car;
    }

    private void checkData(CarDTO dto) throws Exception {
        checkRegistrationValid(dto.getRegistration());
        checkClientExist(dto.getClientId());
    }

    private void checkRegistrationValid(String registration) {
        /*TODO VALIDATION REGISTRATION*/
    }

    private void checkClientExist(int clientId) throws Exception {
        if (clientManager.getById(clientId) == null) {
            throw new EntityNotFoundException("invalid clientId: " + clientId);

        }
    }

    private void checkCarModelExist(int carModelId) throws Exception {
        if (carModelManager.getById(carModelId) == null) {
            throw new EntityNotFoundException("invalid carModelId: " + carModelId);

        }
    }


}
