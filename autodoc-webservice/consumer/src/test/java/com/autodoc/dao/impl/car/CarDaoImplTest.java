package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
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
    Car obj;
    int id;
    Class clazz = Car.class;
    int allSize = 2;
    Client client;
    CarModel carModel;
    Car objFromDb;
    @Inject
    private CarDao dao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private CarModelDao carModelDao;
    @Inject
    private Filler filler;
    @Inject
    private Remover remover;

    @BeforeEach
    void init() throws Exception {
       // remover.remove();
        filler.fill();
        client = (Client) clientDao.getAll().get(0);
        carModel = (CarModel) carModelDao.getAll().get(0);
        obj = Car.builder().registration("abc123").client(client).carModel(carModel).build();
        objFromDb = (Car) dao.getAll().get(0);
        id = objFromDb.getId();
    }

    @AfterEach
    void remove() {

    }


    ///////////  GENERAL  /////////////////////////////////


    @Test
    @DisplayName("should return class name")
    void getClazz() {
        assertEquals(clazz, dao.getClazz());
    }

    @Test
    @DisplayName("should return null if invalid id")
    void getById() {
        assertNull(dao.getById(21));
    }

    @Test
    @DisplayName("should return object if valid id")
    void getById1() {
        assertNotNull(dao.getById(id));
    }

    @Test
    @DisplayName("should return objects if existing")
    void getAll() {
        assertEquals(allSize, dao.getAll().size());
    }

    @Test
    @DisplayName("should add car")
    void create() {
        assertEquals(2, dao.getAll().size());
        dao.create(obj);
        LOGGER.info(dao.create(obj));
        assertEquals(3, dao.getAll().size());
    }

    @Test
    @DisplayName("should return true when deleting object")
    void deleteById() {
        id = obj.getId();
        assertTrue(dao.delete(obj));
        assertNull(dao.getById(id));
    }


    @Test
    @DisplayName("should return false when trying to delete with invalid id")
    void deleteById2() {
        assertFalse(dao.deleteById(233));
    }


    ///////////  SPECIFIC /////////////////////////////////

    @Test
    @DisplayName("should update and return true")
    void update() {
        String newReg = "plakopin";
        obj = (Car) dao.getAll().get(0);
        obj.setRegistration(newReg);
        dao.update(obj);
        Car updatedObj = (Car) dao.getById(obj.getId());
        assertTrue(updatedObj.getRegistration().equalsIgnoreCase(newReg));
    }

    @Test
    @DisplayName("should return true when deleting object by id")
    void deleteById1() {
        Car car = (Car) dao.getAll().get(0);
        System.out.println(car.getId());
        assertTrue(dao.deleteById(car.getId()));
    }


    @Test
    @DisplayName("should return null")
    void getByClient() {

        assertTrue(dao.getCarByClient("toto").isEmpty());
    }

    @Test
    @DisplayName("should return null")
    void getByClient2() {

        assertTrue(dao.getCarByClient("toto").isEmpty());
    }

    @Test
    @DisplayName("should return list of car for a client")
    void getByClient1() {
        assertAll(
                () -> assertFalse(dao.getCarByClient("BAUER").isEmpty())
                //   () -> assertThat(dao.getCarByClient("bauer"), instanceOf(ArrayList.class))
        );
    }

    @Test
    @DisplayName("should return an object")
    void getByRegistration() {
        assertAll(
                () -> assertNotNull(dao.getCarByRegistration(registration)),
                () -> assertThat((dao.getCarByRegistration(registration)), instanceOf(clazz))
        );
    }

    @Test
    @DisplayName("should return null")
    void getByRegistration1() {

        assertNull(dao.getCarByRegistration("dede"));
    }

    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(dao.getSearchField(), Car.getSearchField());
    }
}
