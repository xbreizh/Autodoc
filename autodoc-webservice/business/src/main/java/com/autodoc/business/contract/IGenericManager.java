package com.autodoc.business.contract;

import java.util.List;

public interface IGenericManager<T> {

    //void setClazz(Class<T> clazzToSet);

    T getById(final int id);

    List<T> getAll();

    String save(final T entity);

    String update(final T entity);

    String delete(final T entity);

    String deleteById(final int entityId);
}
