package com.autodoc.controllers;

import com.autodoc.business.contract.CarManager;
import com.autodoc.model.models.car.Car;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarControllerTest {


    private CarController carController;
    private CarManager carManager;

    @BeforeEach
    void init(){
        carController = new CarController();
        carManager = mock(CarManager.class);
        carController.setCarManager(carManager);
    }

    @Test
    void getAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        String response = new Gson().toJson(cars);
        when(carManager.getAll()).thenReturn(cars);
        assertEquals(response, carController.getAll());
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void add() {
    }
}