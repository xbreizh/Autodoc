package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;

/*@Service*/
public interface CarManager extends IGenericManager {


    CarDTO getByRegistration(String registration);


    CarDTO updateClient(int carId, int clientId);

    Car transferUpdate(Object obj);

    void checkClientExist(int clientId);

    void checkCarModelExist(int carModelId);

    boolean checkRegistrationNotInUse(int id, String registration);

    void checkIfExistingCar(String registration);

    Car checkIfCarInDb(CarDTO dto);


}

