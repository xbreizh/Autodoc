package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;

/*@Service*/
public interface CarManager extends IGenericManager {


    CarDTO getByRegistration(String registration);


    CarDTO updateClient(int carId, int clientId) throws Exception;

    Car transferUpdate(Object obj) throws Exception;

}
