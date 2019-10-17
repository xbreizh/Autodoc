package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarManagerImpl<T> extends AbstractGenericManager implements CarManager {
    private CarDaoImpl<Car> carDao;
    private ClientManager clientManager;
    private Logger logger = Logger.getLogger(CarManagerImpl.class);

    public CarManagerImpl(CarDaoImpl dao, ClientManager clientManager) {
        super(dao);
        this.clientManager = clientManager;
        this.carDao = dao;
        logger.info("creating manager");
    }


  /*  public CarManagerImpl(CarDaoImpl<Car> carDao, ClientDaoImpl clientDao) {
        this.carDao = carDao;
        this.clientDao = clientDao;

    }*/


  /*  @Override
    public String save(Car car) {
        logger.debug("trying to save a car");
        logger.info("trying to save a like: " + car);
        try {
            carDao.create(car);
            return "car added";
        } catch (ConstraintViolationException e) {
            logger.debug("error: " + e.getLocalizedMessage());
            return e.getMessage();
        }

    }*/



/*    @Override
    public List<Car> findAll() {
        System.out.println("overwritten");
        return carDao.findAll();
    }*/

    @Override
    public Car getByRegistration(String registration) {
        logger.info("reg: " + registration);
        return carDao.getCarByRegistration(registration);
    }

/*    @Override
    public String update(Car car) {
        try {
            carDao.update(car);
            return "car updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }*/

    @Override
    public String updateClient(int carId, int clientId) {
        Car car = (Car) carDao.getById(carId);
        Client client = (Client) clientManager.getById(clientId);
        if (client == null) return "no client found";
        if (car == null) return "no car found";
        car.setClient(client);
        return update(car);
    }

   /* @Override
    public Car getById(int id) {
        logger.info("id passed: " + id);
        Car car = (Car) carDao.getById(id);
        logger.info("car found: " + car);
        return car;
    }*/


}
