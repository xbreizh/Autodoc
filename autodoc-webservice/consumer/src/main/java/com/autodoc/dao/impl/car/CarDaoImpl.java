package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarDaoImpl<T> extends AbstractHibernateDao implements CarDao {
    private Logger logger = Logger.getLogger(CarDaoImpl.class);

    public CarDaoImpl() {
        this.setClazz(Car.class);
    }

    @Override
    public Car getCarByRegistration(String registration) {
        logger.debug("getting car by Registration: "+registration);
        Query query = getCurrentSession().createQuery("From Car where registration = :registration");
        query.setParameter("registration", registration);
        List<Car> cars = query.getResultList();
        if(cars.size()>0)return cars.get(0);
        logger.debug("no car found");
        return null;
    }

    @Override
    public List<Car> getCarByClient(String lastName) {
        Query query = getCurrentSession().createQuery("From Car where Client.lastName= :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();

    }


}
