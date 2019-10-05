package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarDaoImpl<T extends Serializable> extends AbstractHibernateDao implements CarDao {
    private Logger logger = Logger.getLogger(CarDaoImpl.class);
    public CarDaoImpl() {
        this.setClazz(Car.class);
    }

    @Override
    public Car getCarByRegistration(String registration) {
        logger.debug("getting car by Registration");
        Query query = getCurrentSession().createQuery("From Car where registration = :registration");
        query.setParameter("registration", registration);
        return (Car) query.getResultList().get(0);
    }

    @Override
    public List<Car> getCarByClient(String lastName) {
        Query query = getCurrentSession().createQuery("From Car where Client.lastName= :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
