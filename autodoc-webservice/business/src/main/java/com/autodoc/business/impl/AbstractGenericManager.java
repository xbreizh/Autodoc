package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public abstract class AbstractGenericManager<T, D> implements IGenericManager<T, D> {

    private Logger logger = Logger.getLogger(AbstractGenericManager.class);
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


    public String save(D object) throws Exception {
        logger.info("trying to save a " + object.getClass());
        T objectToSave = dtoToEntity((D) object);
        System.out.println("save: " + objectToSave);
        String feedback = dao.create(objectToSave);
        System.out.println("re: " + feedback);
        if (feedback.isEmpty()) {
            return object.getClass().getSimpleName() + " added";
        }
            /*.equals("");
            return object.getClass().getSimpleName() + " added";
        } catch (ConstraintViolationException e) {
            System.out.println("error: "+e.getLocalizedMessage());
            System.out.println(e.getMessage());
            logger.debug("error: " + e.getLocalizedMessage());
            System.out.println("eee: "+e.getStackTrace());
            System.out.println("rr: "+e.getSQL());
            return e.getMessage();
        }*/
        System.out.println("ra: " + feedback);
        return feedback;
          /*  logger.info("ee: "+feedback+"fin");
            Exception exception = new Exception(feedback);
        System.out.println("dede: "+exception.getMessage());
            throw exception;*/
        // return feedback;

    }

/*
    @Override
    public void setClazz(Class clazzToSet) {

    }*/

    @Override
    public D getById(int id) {
        return entityToDto(dao.getById(id));
    }

    @Override
    public List getAll() {
        logger.info("trying to find them all");
        logger.debug("dao: " + dao);

        return convertList(dao.getAll());
    }

    private List<D> convertList(List<T> list) {
        List<D> newList = new ArrayList<>();
        for (T obj : dao.getAll()) {
            Object newObj = entityToDto(obj);
            newList.add((D) newObj);
        }
        return newList;
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
        logger.info("trying to delete " + entityId);
        try {
            dao.deleteById(entityId);
            System.out.println("sccuees");
            return "car deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
