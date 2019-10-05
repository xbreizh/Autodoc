package com.autodoc.business.contract.car;

import com.autodoc.model.models.car.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarModelManager {


    List<CarModel> getAll();

    CarModel getById(int id);
}
