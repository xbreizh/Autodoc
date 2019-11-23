package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarDaoImpl<T> extends AbstractHibernateDao implements CarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDaoImpl.class);

    public CarDaoImpl() {
        this.setClazz(Car.class);
    }

    public Map<String, SearchType> getSearchField() {

        return  Car.SEARCH_FIELD;
    }

    @Override
    public Car getCarByRegistration(String registration) {
        LOGGER.debug("getting car by Registration: " + registration);
        Query query = getCurrentSession().createQuery("From Car where registration = :registration");
        query.setParameter("registration", registration);
        List<Car> cars = query.getResultList();
        if (cars.size() > 0) return cars.get(0);
        LOGGER.debug("no car found");
        return null;
    }

    @Override
    public List<Car> getCarByClient(String lastName) {
        lastName = lastName.toUpperCase();
        System.out.println("Last:" + lastName);
        Query query = getCurrentSession().createQuery("From Car where client.lastName= :lastName");
        query.setParameter("lastName", lastName);
        if (query.getResultList() == null) return new ArrayList<>();
        return query.getResultList();

    }

 /*   public List<Car> getByCriteria() {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Car> cr = cb.createQuery(Car.class);
        Root<Car> root = cr.from(Car.class);
        cr.select(root);

        Query<Car> query = getCurrentSession().createQuery(cr);
        List<Car> results = query.getResultList();
        return results;
    }
*/

}
