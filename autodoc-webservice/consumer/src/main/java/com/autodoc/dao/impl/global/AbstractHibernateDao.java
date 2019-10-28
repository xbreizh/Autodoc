package com.autodoc.dao.impl.global;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

public abstract class AbstractHibernateDao<T> {
    @Inject
    SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);


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
        LOGGER.debug("class: " + clazz.getName());
        LOGGER.debug("getting all");
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }


    public int create(T entity) {
        try {

            return (Integer) getCurrentSession().save(entity);
           /* int i = (Integer) ser;
            System.out.println("id foung: "+i);
            //System.out.println("Id to return: "+idToReturn);
            return "";*/
        } catch (Exception e) {
            return 0;
        }
    }

    public String delete(T entity) {
        System.out.println("entity: " + entity);
        getCurrentSession().delete(entity);
        return "";

    }

    public String update(T entity) {
        T obj = (T) getCurrentSession().merge(entity);
        if (obj != null) return "updated";
        return null;
    }

    //@Override
    public T getByName(String name) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName() + "where name = :name");
        query.setParameter(name, name);
        if (query.getResultList().isEmpty()) return null;
        return (T) query.getResultList().get(0);
    }

    public String deleteById(final int entityId) {
        T entity = getById(entityId);
        delete(entity);
        return "";
    }

    protected Session getCurrentSession() {
        LOGGER.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

}
