/*
package com.autodoc.dao.impl.global;

import com.autodoc.model.models.car.Car;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class AbstractHibernateDaoTest {

    @Inject
    GenericHibernateDao genericHibernateDao;

    @Test
    void setClazz() {
        genericHibernateDao.setClazz(Car.class);
        assertEquals(Car.class, genericHibernateDao.getClazz());
    }

    @Test
    void findOne() {
        Car car = new Car();
        genericHibernateDao.setClazz(Car.class);
        genericHibernateDao.create(car);
        assertEquals(car, genericHibernateDao.findAll().get(genericHibernateDao.findAll().size()-1));
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getCurrentSession() {
    }
}*/
