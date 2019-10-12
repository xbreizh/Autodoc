package com.autodoc.dao.impl.global;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {
    @Inject
    SessionFactory sessionFactory;

    public Class<Serializable> getClazz() {
        return clazz;
    }

    private Class<Serializable> clazz;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;
    }


    public T findOne(int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        System.out.println("class: "+clazz.getName());
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }


    public Serializable create(Serializable entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public void delete(Serializable entity) {
        getCurrentSession().delete(entity);

    }

    public Serializable update(Serializable entity) {
        return (T) getCurrentSession().merge(entity);
    }


    public void deleteById(final int entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
