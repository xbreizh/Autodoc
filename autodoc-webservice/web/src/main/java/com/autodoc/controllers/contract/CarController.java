package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import org.springframework.http.ResponseEntity;

public interface CarController {


    String getAll();

    Car getCarByRegistration(String registration);

    String getCarById(int id);

    String getCarByClient(String clientLastName, String clientFirstName);

    ResponseEntity addCar(Car car);

    String updateCar();

    String updateCarClient(Client client);

    String deleteCar(int carId);
}
