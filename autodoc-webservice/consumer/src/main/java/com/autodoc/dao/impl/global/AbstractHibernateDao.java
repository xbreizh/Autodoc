package com.autodoc.dao.impl.global;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

public abstract class AbstractHibernateDao<T> {
    @Inject
    SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(AbstractHibernateDao.class);


    public Class<Object> getClazz() {
        return clazz;
    }

    private Class<Object> clazz;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;

    }


    public T getById(int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        logger.debug("class: " + clazz.getName());
        logger.debug("getting all");
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }


    public Object create(Object entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public void delete(Object entity) {
        System.out.println("entity: "+entity);
        getCurrentSession().delete(entity);

    }

    public Object update(Object entity) {
        return (T) getCurrentSession().merge(entity);
    }


    public void deleteById(final int entityId) {
        T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        logger.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

}
