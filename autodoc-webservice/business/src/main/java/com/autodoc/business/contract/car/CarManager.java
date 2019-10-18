package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarDTO;
import org.springframework.stereotype.Service;

@Service
public interface CarManager extends IGenericManager {


    // String save(Car car);

    // List<Car> getAll();

    CarDTO getByRegistration(String registration);

    // String update(Car car);

    String updateClient(int carId, int clientId);

    // Car getById(int id);
}
