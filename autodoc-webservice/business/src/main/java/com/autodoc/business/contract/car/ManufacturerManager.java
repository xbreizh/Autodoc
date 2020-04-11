package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import org.springframework.stereotype.Service;

@Service
public interface ManufacturerManager extends IGenericManager {



    ManufacturerDTO getByName(String anyString);
}
