package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarDaoImplTest {

    @Inject
    private CarDao carDao;


    @BeforeEach
    void init() {

        String name = "PLOP";

    }

    @Test
    void getAll() {
        assertEquals(2, carDao.getAll().size());
    }

   /* @Test
    void getByCriteria() {

        assertEquals(1, carDao.getByCriteria("D").size());
    }*/
}