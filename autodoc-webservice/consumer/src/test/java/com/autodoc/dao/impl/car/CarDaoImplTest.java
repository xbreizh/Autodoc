package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
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
@Transactional
class CarDaoImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarDaoImplTest.class);

    String registration = "05D121487";
    int id;
    Car car;
    @Inject
    private CarDao dao;
    @Inject
    private Filler filler;
    @Inject
    private Remover remover;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
    }

    @AfterEach
    void remove() {
        remover.remover();
    }


    @Test
    void getAll() {
        assertEquals(2, dao.getAll().size());
    }

    @Test
    @DisplayName("should return a car")
    void getByRegistration() {
        assertAll(
                () -> assertNotNull(dao.getCarByRegistration(registration)),
                () -> assertThat((dao.getCarByRegistration(registration)), instanceOf(Car.class))
        );
    }

    @Test
    void deleteById() {
        int id = 2;
        dao.deleteById(id);
        assertNull(dao.getById(id));
    }


    @Test
    @DisplayName("should return null")
    void getByRegistration1() {

        assertNull(dao.getCarByRegistration("dede"));
    }


    @Test
    @DisplayName("should return null")
    void getById() {

        assertNotNull(dao.getById(1));
    }


    @Test
    @DisplayName("should add car")
    void add() {
        assertEquals(2, dao.getAll().size());
        Car car = new Car();
        CarModel carModel = CarModel.builder().build();
        carModel.setId(1);
        Client client = new Client();
        client.setId(1);
        car.setClient(client);
        car.setCarModel(carModel);
        car.setRegistration("abc123");
        LOGGER.info(dao.create(car));
        assertEquals(3, dao.getAll().size());
    }


    @Test
    @DisplayName("should return null")
    void getByClient() {

        assertTrue(dao.getCarByClient("toto").isEmpty());
    }

 /* @Test
    @DisplayName("should return list of car for a client")
    void getByClient1() {
        assertAll(
                () -> assertFalse(dao.getCarByClient("BAUER").isEmpty())
             //   () -> assertThat(dao.getCarByClient("bauer"), instanceOf(ArrayList.class))
        );
    }*/
}
