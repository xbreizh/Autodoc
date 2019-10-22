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


    public String create(T entity) {
        try {
            getCurrentSession().saveOrUpdate(entity);
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String delete(T entity) {
        System.out.println("entity: " + entity);
        getCurrentSession().delete(entity);
        return "";

    }

    public String update(T entity) {
        getCurrentSession().merge(entity);
        return "";
    }


    public String deleteById(final int entityId) {
        T entity = getById(entityId);
        delete(entity);
        return "";
    }

    protected Session getCurrentSession() {
        logger.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

}
