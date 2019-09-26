package com.autodoc.dao.contract.global;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

    void setClazz(Class<T> clazzToSet);

    T findOne(final int id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final int entityId);

    void setSessionFactory(SessionFactory sessionFactory);

}