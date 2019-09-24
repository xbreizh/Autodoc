package com.autodoc.business.contract;

import com.autodoc.model.models.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarManager {


    boolean save(Car car);
}
