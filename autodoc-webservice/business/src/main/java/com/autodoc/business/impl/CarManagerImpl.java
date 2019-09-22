package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.dao.contract.CarDao;
import com.autodoc.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CarManagerImpl implements CarManager {


      @Autowired
      private CarDao carDao;
    @Override
    public boolean save(Car car) {
        System.out.println("car mger: " + car);
        carDao.add(car);
        return true;
    }
}
