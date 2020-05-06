package com.autodoc.dao.impl.global;

import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.exceptions.DaoException;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class AbstractHibernateDaoTest {


    SessionFactory sessionFactory = mock(SessionFactory.class);
    Session session = mock(Session.class);
    @Inject
    private TestAbstractDaoImpl dao;
    @Inject
    private CarDaoImpl carDao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private Filler filler;
    // @Inject
    private AbstractHibernateDao mockDao;

    @Inject
    SessionFactory factory;

    @BeforeEach
    void init() throws ParseException {
        BasicConfigurator.configure();
        filler.fill();
        mockDao = mock(AbstractHibernateDao.class);
        mockDao.setSessionFactory(sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(session);


    }


    @Test
    void getSessionFactory() {
        dao.setSessionFactory(sessionFactory);
        assertEquals(sessionFactory, dao.getSessionFactory());
    }

    @Test
    void setSessionFactory() {
        dao.setSessionFactory(sessionFactory);
        assertEquals(sessionFactory, dao.getSessionFactory());
    }

    @Test
    void create() {
        when(session.save(any())).thenThrow(new NumberFormatException());
        assertEquals(0, mockDao.create(new Car()));
    }

    @Test
    @DisplayName("should return an exception")
    void create1() {
        assertEquals(0, mockDao.create(new Car()));

    }


    @Test
    void update() {
        dao.setSessionFactory(sessionFactory);
        when(session.merge(any())).thenReturn(null);
        assertTrue(dao.update(new Car()));
    }


    @Test
    void update1() {
        dao.setSessionFactory(sessionFactory);
        when(session.merge(any())).thenReturn(new Car());
        assertTrue(dao.update(new Car()));
    }

    @Test
    void update2() {
        Car car = (Car) carDao.getAll().get(0);
        int id = car.getId();
        System.out.println(car);

        Client client = (Client) clientDao.getById(2);
        car.setClient(client);
        carDao.update(car);
        assertEquals(client, ((Car) carDao.getById(id)).getClient());
    }

    @Test
    @DisplayName("should throw an exception if search null")
    void getByCriteria() {
        assertThrows(DaoException.class, () -> dao.getByCriteria(null));
    }

    @Test
    @DisplayName("should throw an exception if no search criteria available for an entity")
    void getByCriteria1() {
        List<Search> searchList = new ArrayList<>();
        assertThrows(DaoException.class, () -> dao.getByCriteria(searchList));
    }

    @Test
    @DisplayName("should return list if valid search")
    void getByCriteria2() {
        String superRequest = "superRequest";
        List<Car> list = new ArrayList<>();
        List<Search> searchList = new ArrayList<>();
        searchList.add(new Search("crit", "compare", "value"));
        when(mockDao.getSearchField()).thenReturn(Car.getSearchField());
        assertEquals(list, mockDao.getByCriteria(searchList));
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
    @DisplayName("should return null for any name passed")
    void getByName() {
        assertNull(dao.getByName("plok"));
    }

    @Test
    @DisplayName("should return null for any name passed")
    void getById() {
        assertNull(dao.getById(32));
    }

    @Test
    @DisplayName("should return null when getting search Fields")
    void getSearchField() {
        assertNull(dao.getSearchField());
    }

    @Test
    @DisplayName("should return criteria request")
    void buildCriteriaRequest() {

        List<Search> searchList = new ArrayList<>();
        searchList.add(new Search("fieldName", "compare", "value"));
        searchList.add(new Search("fieldName1", "compare1", "value1"));
        assertEquals("from Car where fieldName compare 'value' and fieldName1 compare1 'value1'", carDao.buildCriteriaRequest(searchList));
    }


}
