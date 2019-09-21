package com.autodoc.dao.impl;


import com.autodoc.dao.contract.CarDao;
import com.autodoc.model.Car;
import com.autodoc.model.Client;
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
        Client client = new Client();
        //client.setId(2);
        client.setFirstName("bob");
        sessionFactory.getCurrentSession().save(client);
        return true;
    }
}
