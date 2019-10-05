package com.autodoc.business.contract;

import com.autodoc.model.models.car.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarModelManager {


    List<CarModel> getAll();

}
