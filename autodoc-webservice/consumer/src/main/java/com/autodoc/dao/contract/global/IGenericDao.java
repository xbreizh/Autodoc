package com.autodoc.dao.contract.global;

import java.util.List;

public interface IGenericDao<T> {

    void setClazz(Class<T> clazzToSet);

    T getById(final int id);

    List<T> getAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final int entityId);


}