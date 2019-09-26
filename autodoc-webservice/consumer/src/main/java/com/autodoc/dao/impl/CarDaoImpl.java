package com.autodoc.dao.impl;

import com.autodoc.dao.contract.CarDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarDaoImpl<T extends Serializable> extends AbstractHibernateDao implements CarDao {

    public CarDaoImpl() {
        this.setClazz(Car.class);
    }

    @Override
    public Car getCarByRegistration(String registration) {
        System.out.println("getting car by Registration");
        Query query = getCurrentSession().createQuery("From Car where registration = :registration");
        query.setParameter("registration", registration);
        return (Car) query.getResultList().get(0);
    }
}
