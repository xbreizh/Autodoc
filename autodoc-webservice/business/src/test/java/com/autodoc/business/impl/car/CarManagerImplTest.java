package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarManagerImplTest {

    // @Inject
    private CarManager carManager;
    //@Inject
    private CarDao carDao;
    private ClientDao clientDao;
    //@Inject
    private ClientManager clientManager;
    // @Inject
    private CarModelManager carModelManager;
    private Car car;


    @BeforeEach
    void init() {
        //clientDao= new ClientDaoImpl<>();
        carDao = mock(CarDaoImpl.class);
        carModelManager = mock(CarModelManager.class);
        clientManager = mock(ClientManager.class);
        clientDao = mock(ClientDao.class);
        // System.out.println(carDao);
        //System.out.println(clientDao);
        //clientManager = new ClientManagerImpl<Client, ClientDTO>(clientDao);
        carManager = new CarManagerImpl(clientManager, carDao, carModelManager);
        //System.out.println(clientManager);
        //System.out.println(carManager);
        car = new Car();
    }

    @Test
    @DisplayName("should return null if registration not found")
    void getByRegistration() {
        String reg = "dede";
        when(carDao.getCarByRegistration(reg)).thenReturn(null);
        assertNull(carManager.getByRegistration(reg));
    }


    @Test
    @DisplayName("should return car if registration found")
    void getByRegistration1() {
        System.out.println("dao: " + carDao);
        String reg = "dede";
        when(carDao.getCarByRegistration(reg)).thenReturn(new Car());
        assertNotNull(carManager.getByRegistration(reg));
    }


    @Test
    void save() throws Exception {
        String registration = "abc123";
        String id = "1111";
        CarDTO dto = new CarDTO();
        dto.setRegistration(registration);
        dto.setClientId(2);
        dto.setId(3);
        dto.setCarModelId(3);
        when(clientManager.getById(anyInt())).thenReturn(new Client());
        when(carModelManager.getById(anyInt())).thenReturn(new CarModel());
        when(carDao.create(any(Car.class))).thenReturn(Integer.parseInt(id));
        assertEquals(id, carManager.save(dto));
    }

    @Test
    void getAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car());
        carList.add(new Car());
        when(carDao.getAll()).thenReturn(carList);
        assertAll(
                () -> assertNotNull(carManager.getAll()),
                () -> assertEquals(2, carManager.getAll().size())
        );
    }


    @Test
    void update() throws Exception {
        String registration = "abc123";
        CarDTO dto = new CarDTO();
        dto.setRegistration(registration);
        dto.setClientId(2);
        dto.setId(3);
        dto.setCarModelId(3);
        when(carDao.update(any(Car.class))).thenReturn(true);
        when(clientManager.getById(anyInt())).thenReturn(new Client());
        when(carModelManager.getById(anyInt())).thenReturn(new CarModel());
        assertTrue(carManager.update(dto));
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
