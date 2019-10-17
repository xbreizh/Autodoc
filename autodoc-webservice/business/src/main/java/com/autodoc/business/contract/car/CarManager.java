package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarManager extends IGenericManager {


    // String save(Car car);

    // List<Car> getAll();

    Car getByRegistration(String registration);

    // String update(Car car);

    String updateClient(int carId, int clientId);

    // Car getById(int id);
}
