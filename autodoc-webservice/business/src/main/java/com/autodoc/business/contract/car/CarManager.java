package com.autodoc.business.contract.car;

import com.autodoc.model.models.car.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarManager {


    String save(Car car);

    List<Car> getAll();

    Car getByRegistration(String registration);

    String update(Car car);
}
