package com.autodoc.dao.impl;


import com.autodoc.dao.contract.CarDao;
import com.autodoc.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class CarDaoImpl implements CarDao {

    private SessionFactory sessionFactory;

    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public boolean add(Car car) {
        System.out.println(sessionFactory.getCurrentSession());
        System.out.println("cardaO: "+car);
        sessionFactory.getCurrentSession().save(car);
        return true;
    }
}
