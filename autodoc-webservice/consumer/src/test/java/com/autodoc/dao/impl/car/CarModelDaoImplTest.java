package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarModelDaoImplTest {

    @Inject
    private ManufacturerDao manufacturerDao;

    @Inject
    private CarModelDao carModelDao;

    private Manufacturer man;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        man = (Manufacturer) manufacturerDao.getAll().get(0);

    }


    @Test
    void findAll() {
        assertEquals(3, carModelDao.getAll().size());

    }

    @Test
    void create() {
        String name = "BALOO";
        assertNull(carModelDao.findByName(name));
        CarModel cm = new CarModel(man, name, "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL);
        carModelDao.create(cm);
        assertNotNull(carModelDao.findByName(name));
    }

    @Test
    void delete() {
        String name = "BALOO";
        CarModel cm = new CarModel(man, name, "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL);
        carModelDao.create(cm);
        assertNotNull(carModelDao.findByName(name));
        carModelDao.delete(cm);
        assertNull(carModelDao.findByName(name));
    }

    @Test
    void update() {
        String name = "BALOO";
        String description = "new description";
        CarModel cm = new CarModel(man, name, "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL);
        carModelDao.create(cm);
        cm.setDescription(description);
        carModelDao.update(cm);
        assertEquals(description, carModelDao.findByName(name).getDescription());
    }

    @Test
    void deleteById() {
        String name = "BALOO";
        CarModel cm = new CarModel(man, name, "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL);
        carModelDao.create(cm);
        int id = carModelDao.findByName(name).getId();
        assertNotNull(carModelDao.getById(id));
        carModelDao.deleteById(id);
        assertNull(carModelDao.getById(id));
    }

    @Test
    void findByName() {
        String name = "BALOO";
        CarModel cm = new CarModel(man, name, "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL);
        carModelDao.create(cm);
        int id = carModelDao.findByName(name).getId();
        assertNotNull(carModelDao.findByName(name));
    }
}