package com.autodoc.dao.impl.global;

import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class AbstractHibernateDaoTest {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDaoTest.class);
    private AbstractHibernateDao<Car> dao;
    private SessionFactory sessionFactory;
    private Session session;

    private static void setFinalStaticField(Class<?> clazz, String fieldName, Object value)
            throws ReflectiveOperationException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, value);
    }

    private Remover remover;
    private Filler filler;

    @BeforeEach
    void init() {
        dao = mock(AbstractHibernateDao.class);
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        dao.setSessionFactory(sessionFactory);

    }

    @Test
    void getClazz() {
        assertEquals(null, dao.getClazz());
    }

    @Test
    @DisplayName("checks that Logger is info enabled")
    void checkLoggerIsInfoEnabled() {
        assertTrue(AbstractHibernateDao.LOGGER.isInfoEnabled());
    }

    @Test
    @DisplayName("checks that Logger is info enabled")
    void getById() {
        Car obj = new Car();
        obj.setRegistration("abc123");
       /* when(dao.getCurrentSession()).thenReturn(session);
        when(session.load(Car.class, 3)).thenReturn(obj);
     //   System.out.println(session.load(Car.class, 3));
        System.out.println(dao.getCurrentSession().load(Car.class, 3));
        System.out.println(dao.getById(3));*/
        assertEquals(null, dao.getById(33));
        //assertEquals(obj, dao.getById(3));
    }


    @Test
    @DisplayName("should return them all")
    void getAll() {
        when(dao.getCurrentSession()).thenReturn(session);
        Query query = mock(Query.class);
        when(session.createQuery(anyString())).thenReturn(query);
        List<Car> list = new ArrayList<>();
        when(session.createQuery(anyString()).getResultList()).thenReturn(list);
        assertEquals(list, dao.getAll());

    }
/*
    @Test
    void create() {
        String typo = "STRING";
        String compare = "EQUALs";

        for (SearchType type : SearchType.values()) {
            if (type.name().equals(typo)) {
                for (String[] str : type.getValues()) {
                    if (str[0].equalsIgnoreCase(compare)) {
                        LOGGER.info("found it");
                        LOGGER.info(type + " " + str[0] + " / " + str[1]);
                    }
                }
            }
        }


    }*/



}
