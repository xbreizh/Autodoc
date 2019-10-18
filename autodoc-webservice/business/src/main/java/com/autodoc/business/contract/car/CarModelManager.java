package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.car.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarModelManager extends IGenericManager {


    List<CarModel> getAll();

    CarModel getById(int id);

    CarModel getByName(String name);
}
