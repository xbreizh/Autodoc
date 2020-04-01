package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class CarManagerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImplTest.class);

    private CarManager carManager;
    private CarDao dao;
    private ClientDao clientDao;
    private CarModelDao carModelDao;
    private Car car;


   /* @BeforeEach
    void init() {
        dao = mock(CarDaoImpl.class);
        carModelDao = mock(CarModelDao.class);
        clientDao = mock(ClientDao.class);
        carManager = new CarManagerImpl(clientDao, dao, carModelDao);
        car = new Car();
    }*/

    @Test
    @DisplayName("should return null if registration not found")
    void getByRegistration() {
        String reg = "dede";
        when(dao.getCarByRegistration(reg)).thenReturn(null);
        assertNull(carManager.getByRegistration(reg));
    }


    @Test
    @DisplayName("should return car if registration found")
    void getByRegistration1() {
        LOGGER.info("dao: " + dao);
        String reg = "DEDE";
        when(dao.getCarByRegistration(reg)).thenReturn(new Car());
        assertNotNull(carManager.getByRegistration("dede"));
    }


    @Test
    void save() throws Exception {
        String registration = "abc123";
        int id = 111;
        CarDTO dto = new CarDTO();
        dto.setRegistration(registration);
        dto.setClientId(2);
        dto.setId(3);
        dto.setCarModelId(3);
        when(clientDao.getById(anyInt())).thenReturn(new Client());
        when(carModelDao.getById(anyInt())).thenReturn(new CarModel());
        when(dao.create(any(Car.class))).thenReturn(id);
        assertEquals(Integer.toString(id), carManager.save(dto));
    }

    @Test
    void getAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car());
        carList.add(new Car());
        when(dao.getAll()).thenReturn(carList);
        assertAll(
                () -> assertNotNull(carManager.getAll()),
                () -> assertEquals(2, carManager.getAll().size())
        );
    }


    @Test
    void update() throws Exception {
        String registration = "abc123";
        int id = 5;
        car.setId(id);
        car.setRegistration(registration);
        CarDTO dto = new CarDTO();
        dto.setRegistration(registration);
        dto.setClientId(2);
        dto.setId(3);
        dto.setCarModelId(3);
        when(dao.getById(anyInt())).thenReturn(car);
        when(dao.update(any(Car.class))).thenReturn(true);
        when(clientDao.getById(anyInt())).thenReturn(new Client());
        when(carModelDao.getById(anyInt())).thenReturn(new CarModel());
        assertTrue(carManager.update(dto));
    }

    @Test
    void updateClient() {
    }

    @Test
    void entityToDto() {
    }

    @Test
    void dtoToEntity() {
    }

    @Test
    @DisplayName("should update the car")
    void transferUpdate() throws Exception {
        int id = 4;
        int carModelId = 4;
        int clientId = 5;
        String registration = "ABC123";
        Car car = new Car();
        car.setRegistration(registration);
        car.setId(id);
        CarDTO dto = new CarDTO();
        Client client = new Client();
        client.setId(clientId);
        CarModel carModel = new CarModel();
        carModel.setId(carModelId);
        dto.setId(id);
        dto.setCarModelId(carModelId);
        dto.setClientId(clientId);
        when(dao.getById(anyInt())).thenReturn(car);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(dao.getCarByRegistration(anyString())).thenReturn(car);
        carManager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(registration, car.getRegistration()),
                () -> assertEquals(carModelId, car.getCarModel().getId()),
                () -> assertEquals(clientId, car.getClient().getId())
        );

    }

    @Test
    @DisplayName("should update the car")
    void transferUpdate1() throws Exception {
        int id = 4;
        int carModelId = 4;
        int clientId = 5;
        String registration = "ABC123";
        Car car = new Car();
        car.setRegistration(registration);
        car.setId(id);
        CarDTO dto = new CarDTO();
        Client client = new Client();
        client.setId(clientId);
        CarModel carModel = new CarModel();
        carModel.setId(carModelId);
        dto.setId(id);
        dto.setCarModelId(carModelId);
        dto.setClientId(clientId);
        when(dao.getById(anyInt())).thenReturn(car);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(dao.getCarByRegistration(anyString())).thenReturn(car);
        carManager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(registration, car.getRegistration()),
                () -> assertEquals(carModelId, car.getCarModel().getId()),
                () -> assertEquals(clientId, car.getClient().getId())
        );

    }

    @Test
    void checkIfExistingCar() {
    }

    @Test
    void deleteById() throws Exception {
        when(dao.getById(anyInt())).thenReturn(new Manufacturer());
        when(dao.deleteById(anyInt())).thenReturn(true);
        assertTrue(carManager.deleteById(2));
    }
/*
    @Test
    @DisplayName("should return no client found")
    void updateClient() {
        when(clientManager.getById(anyInt())).thenReturn(null);
        assertEquals("no client found", carManager.updateClient(2, 3));
    }*/

  /*  @Test
    @DisplayName("should return no car found")
    void updateClient1() {
        Client client = new Client();
        int clientId = 4;
        when(clientManager.getById(anyInt())).thenReturn(client);
        assertEquals("no car found", carManager.updateClient(2, clientId));
    }

    @Test
    @DisplayName("should return no car found")
    void updateClient2() {
        Client client = new Client();
        Car car = new Car();
        when(clientManager.getById(anyInt())).thenReturn(client);
        when(carDao.getById(anyInt())).thenReturn(car);
        assertEquals("car updated", carManager.updateClient(2, 3));
    }*/
}
