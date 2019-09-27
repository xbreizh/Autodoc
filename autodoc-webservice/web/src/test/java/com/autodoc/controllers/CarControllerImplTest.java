package com.autodoc.controllers;

import com.autodoc.business.contract.CarManager;
import com.autodoc.controllers.impl.CarControllerImpl;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarControllerImplTest {


    private CarControllerImpl carControllerImpl;
    private CarManager carManager;

    @BeforeEach
    void init() {
        carManager = mock(CarManager.class);
        carControllerImpl = new CarControllerImpl(carManager);
        //carController.setCarManager(carManager);
    }

    @Test
    void getAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        String response = new Gson().toJson(cars);
        when(carManager.getAll()).thenReturn(cars);
        assertEquals(response, carControllerImpl.getAll());
    }

    @Test
    void get() {
    }

    @Test
    void update() {
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setName("mazda");
        car.setRegistration("abs");
        car.setCarModel(carModel);

        String response = carControllerImpl.convertObjectIntoGsonObject(car);
        System.out.println("car: " + response);
    }

    @Test
    void addCar() {
        Car car = new Car();
        when(carManager.save(any(Car.class))).thenReturn(true);
        // assertEquals("car added", carControllerImpl.addCar(car));
    }
}