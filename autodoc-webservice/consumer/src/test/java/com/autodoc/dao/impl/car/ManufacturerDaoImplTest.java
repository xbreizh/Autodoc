/*
package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.filler.Filler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class ManufacturerDaoImplTest {

    @Inject
    ManufacturerDao manufacturerDao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }


    @Test
    void getAll() {
        assertEquals(6, manufacturerDao.getAll().size());
    }


    @Test
    void getByName() {
        String name = "TOYOTA";
        assertNotNull(manufacturerDao.getByName(name));
    }
}*/
