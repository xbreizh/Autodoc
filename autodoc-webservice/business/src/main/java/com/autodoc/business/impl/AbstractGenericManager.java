package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public abstract class AbstractGenericManager<T, D> implements IGenericManager<T, D> {

    protected String exception = "";
    private Logger logger = Logger.getLogger(AbstractGenericManager.class);
    private IGenericDao<T> dao;

    public AbstractGenericManager(IGenericDao dao) {
        this.dao = dao;

    }

    protected void resetException() {
        exception = "";
    }


    public String save(D object) {
        logger.info("trying to save a " + object.getClass());
        try {
            T objectToSave = dtoToEntity((D) object);
            if (!exception.isEmpty()) return exception;
            String feedback = dao.create(objectToSave);
            if (feedback.isEmpty()) {
                return object.getClass().getSimpleName() + " added";
            }
            return feedback;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

    }


    @Override
    public D getById(int id) throws Exception {
        if (dao.getById(id) == null) {
            exception ="no record found";
            return null;
        }
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
    public D update(Object entity) throws Exception {

        T obj = dtoToEntity((D) entity);
        if (!exception.isEmpty()) {
            throw new Exception(exception);

        }

        D dto = entityToDto((T) dao.update(obj));
        if (!exception.isEmpty()) return null;
        return dto;

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
        if (dao.getById(entityId) == null) {
            return "notFound";
        }
        dao.deleteById(entityId);
        return "";
    }


}
