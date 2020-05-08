package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarModelDaoImplTest {

    CarModel obj;
    @Inject
    private ManufacturerDao manufacturerDao;
    @Inject
    private CarModelDaoImpl dao;
    private Manufacturer man;
    @Inject
    private Filler filler;

    @BeforeEach
    void init()  throws Exception {
        BasicConfigurator.configure();
        filler.fill();
        obj = (CarModel) dao.getAll().get(0);

    }


    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(dao.getSearchField(), CarModel.getSearchField());
    }


    @Test
    @DisplayName("should return null if invalid name")
    void findByName() {
        assertNull(dao.getByName("invalidName"));
    }

    @Test
    @DisplayName("should return object if valid name")
    void findByName1() {

        assertEquals(obj, dao.getByName(obj.getName()));
    }

}
