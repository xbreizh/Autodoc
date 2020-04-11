package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.CarModelDTO;
import org.springframework.stereotype.Service;

@Service
public interface CarModelManager extends IGenericManager {


    CarModelDTO getByName(String name);
}
