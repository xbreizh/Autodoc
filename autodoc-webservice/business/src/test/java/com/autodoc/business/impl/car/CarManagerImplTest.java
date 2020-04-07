package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class CarManagerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImplTest.class);

    private CarManager manager;
    private CarDao dao;
    private ClientDao clientDao;
    private CarModelDao carModelDao;
    private Car car;
    private int id = 323;
    private String registration = "abc23";
    private Client client;
    private CarModel carModel;
    private List<Bill> bills;


    @BeforeEach
    void init() {
        dao = mock(CarDaoImpl.class);
        carModelDao = mock(CarModelDao.class);
        clientDao = mock(ClientDao.class);
        manager = CarManagerImpl.builder().carModelDao(carModelDao).clientDao(clientDao).dao(dao).build();
        client = Client.builder().firstName("jean").lastName("rocamor").phoneNumber("abc1233").build();
        carModel = CarModel.builder().id(id).name("niamo").build();
        car = Car.builder().id(id).registration(registration).client(client).carModel(carModel).bills(bills).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }


    @Test
    @DisplayName("should return null if registration not found")
    void getByRegistration() {
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertNull(manager.getByRegistration("blop"));
    }


    @Test
    @DisplayName("should return car if registration found")
    void getByRegistration1() {
        when(dao.getCarByRegistration(anyString())).thenReturn(car);
        assertNotNull(manager.getByRegistration("dede"));
    }

    @Test
    @DisplayName("should update the client")
    void updateClient() throws Exception {
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(dao.getById(anyInt())).thenReturn(car);
        assertEquals(client.getId(), manager.updateClient(2, 4).getClientId());
    }


    @Test
    @DisplayName("should throw an exception if no client found")
    void updateClient1() throws Exception {
        when(clientDao.getById(anyInt())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> manager.updateClient(2, 3));
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
        manager.transferUpdate(dto);
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
        manager.transferUpdate(dto);
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
        assertTrue(manager.deleteById(2));
    }


}
