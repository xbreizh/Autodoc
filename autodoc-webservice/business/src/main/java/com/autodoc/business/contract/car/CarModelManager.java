package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.CarModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarModelManager extends IGenericManager {


    //List<CarModelDTO> getAll();

    //CarModelDTO getById(int id);

    CarModelDTO getByName(String name);
}
