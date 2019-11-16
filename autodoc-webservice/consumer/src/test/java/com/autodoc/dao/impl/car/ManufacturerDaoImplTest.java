package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class ManufacturerDaoImplTest {

    @Inject
    ManufacturerDao dao;

    @Test
    void getAll() {
        assertNotNull(dao.getAll());
    }


    @Test
    void getByName() {
        String name = "TOYOTA";
        System.out.println(dao.getByName(name));
    }
}