package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;

public interface CarController {


    String getAll();

    String getCarByRegistration(String registration);

    String getCarById(int id);

    String getCarByClient(String clientLastName, String clientFirstName);

    String addCar(Car car);

    String updateCar();

    String updateCarClient(Client client);

    String deleteCar(int carId);
}
