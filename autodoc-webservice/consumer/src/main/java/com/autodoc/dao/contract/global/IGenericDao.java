package com.autodoc.dao.contract.global;

import com.autodoc.model.models.search.Search;

import java.util.List;

public interface IGenericDao<T> {

    void setClazz(Class<T> clazzToSet);

    T getById(final int id);

    List<T> getAll();

    int create(final T entity);

    String update(final T entity);

    String delete(final T entity);

    String deleteById(final int entityId);


    T getByName(String name);

    List<T> getByCriteria(List<Search> search) throws Exception;
}