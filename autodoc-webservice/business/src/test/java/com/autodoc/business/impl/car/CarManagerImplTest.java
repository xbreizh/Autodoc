package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarManagerImplTest {

    private CarManager carManager;
    private CarDaoImpl carDao;
    private ClientDaoImpl clientDao;
    private Car car;


    @BeforeEach
    void init() {
        carDao = mock(CarDaoImpl.class);
        clientDao = mock(ClientDaoImpl.class);
        carManager = new CarManagerImpl(carDao, clientDao);
        car = new Car();
    }

    @Test
    void save() {
        String registration = "abc123";
        car.setRegistration(registration);
        when(carDao.create(car)).thenReturn(car);
        assertEquals("car added", carManager.save(car));
    }

    @Test
    void getAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carDao.findAll()).thenReturn(carList);
        assertNotNull(carManager.getAll());
    }

    @Test
    void getByRegistration() {
        String registration = "abc123";
        car.setRegistration(registration);
        when(carDao.getCarByRegistration(anyString())).thenReturn(car);
        assertEquals(car, carManager.getByRegistration(registration));
    }

    @Test
    void update() {
        String registration = "abc123";
        car.setRegistration(registration);
        when(carDao.update(car)).thenReturn(car);
        assertEquals("car updated", carManager.update(car));
    }

    @Test
    @DisplayName("should return no client found")
    void updateClient() {
        when(clientDao.findOne(anyInt())).thenReturn(null);
        assertEquals("no client found", carManager.updateClient(2, 3));
    }

    @Test
    @DisplayName("should return no car found")
    void updateClient1() {
        Client client = new Client();
        int clientId = 4;
        when(clientDao.findOne(anyInt())).thenReturn(client);
        assertEquals("no car found", carManager.updateClient(2, clientId));
    }

    @Test
    @DisplayName("should return no car found")
    void updateClient2() {
        Client client = new Client();
        Car car = new Car();
        when(clientDao.findOne(anyInt())).thenReturn(client);
        when(carDao.findOne(anyInt())).thenReturn(car);
        assertEquals("car updated", carManager.updateClient(2, 3));
    }
}