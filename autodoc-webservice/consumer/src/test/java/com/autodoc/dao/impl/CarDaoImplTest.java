/*
package com.autodoc.dao.impl;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarDaoImplTest {

    @Inject
    private CarDao carDao;

    @BeforeEach
    void init(){
       // carDao.setClazz(Car.class);
    }


    @Test
    void getCarByRegistration() {
        assertNotNull(carDao.getCarByRegistration("morning"));

    }

 */
/*   @Test
    @Disabled
    void getCarByClient() {
        assertNotNull(carDao.getCarByClient("john"));

    }*//*



}*/
