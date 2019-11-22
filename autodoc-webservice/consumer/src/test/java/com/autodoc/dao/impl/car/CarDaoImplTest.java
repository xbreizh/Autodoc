package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarDaoImplTest {

    @Inject
    private CarDao carDao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        String name = "PLOP";

    }

    @Test
    void getAll() {
        assertEquals(2, carDao.getAll().size());
    }

    @Test
    @DisplayName("should return a car")
    void getByRegistration() {
        assertAll(
                () -> assertNotNull(carDao.getCarByRegistration("D12447F")),
                () -> assertThat((carDao.getCarByRegistration("D12447F")), instanceOf(Car.class))
        );

        assertNotNull(carDao.getCarByRegistration("D12447F"));
    }

    @Test
    @DisplayName("should return null")
    void getByRegistration1() {

        assertNull(carDao.getCarByRegistration("dede"));
    }
}