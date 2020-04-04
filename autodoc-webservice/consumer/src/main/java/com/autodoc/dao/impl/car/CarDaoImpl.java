package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class CarDaoImpl extends AbstractHibernateDao implements CarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDaoImpl.class);
    private Class<?> cl = Car.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {
        return Car.getSearchField();
    }

    @Override
    public Car getCarByRegistration(String registration) {
        LOGGER.debug("getting car by Registration: " + registration);
        TypedQuery<Car> query = getCurrentSession().createQuery("From Car where registration = :registration");
        query.setParameter("registration", registration);
        List<Car> cars = query.getResultList();
        if (!cars.isEmpty()) return cars.get(0);
        LOGGER.debug("no car found");
        return null;
    }

    @Override
    public List<Car> getCarByClient(String lastName) {
        lastName = lastName.toUpperCase();
        LOGGER.info("Last:" + lastName);
        System.out.println("last: " + lastName);
        TypedQuery<Car> query = getCurrentSession().createQuery("From Car where client.lastName= :lastName");
        query.setParameter("lastName", lastName);
        if (query.getResultList() == null) return new ArrayList<>();
        return query.getResultList();

    }


}
