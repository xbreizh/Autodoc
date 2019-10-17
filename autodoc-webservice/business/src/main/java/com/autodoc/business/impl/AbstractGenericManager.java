package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public abstract class AbstractGenericManager<T> implements IGenericManager {

    private Logger logger = Logger.getLogger(AbstractHibernateDao.class);
    //private Class<Object> clazz;
    private IGenericDao<T> dao;

    public AbstractGenericManager(IGenericDao dao) {

        this.dao = dao;
    }

    //@Inject
    // AbstractHibernateDao<T> dao;




   /* public Class<Object> getClazz() {
        return clazz;
    }*/


    public String save(Object object) {
        logger.debug("trying to save a " + object.getClass());
        try {
            dao.create((T) object);
            return object.getClass().getSimpleName() + " added";
        } catch (ConstraintViolationException e) {
            logger.debug("error: " + e.getLocalizedMessage());
            return e.getMessage();
        }

    }

/*
    @Override
    public void setClazz(Class clazzToSet) {

    }*/

    @Override
    public Object getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List getAll() {
        logger.info("trying to find them all");
        logger.debug("dao: " + dao);
        return dao.getAll();
    }


    @Override
    public String update(Object entity) {
        try {
            dao.update((T) entity);
            return "car updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(Object entity) {
        try {
            dao.delete((T) entity);
            return "car deleted";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String deleteById(int entityId) {
        try {
            dao.deleteById((entityId));
            return "car deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
