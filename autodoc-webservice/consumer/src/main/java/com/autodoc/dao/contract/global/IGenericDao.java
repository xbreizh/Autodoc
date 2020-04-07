package com.autodoc.dao.contract.global;

import com.autodoc.dao.exceptions.DaoException;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;

import java.util.List;
import java.util.Map;

public interface IGenericDao<T> {

    Class<?> getClazz();

    T getById(final int id);

    List<T> getAll();

    int create(final T entity);

    boolean update(final T entity);

    boolean delete(final T entity);

    boolean deleteById(final int entityId);

    T getByName(String name);

    List<T> getByCriteria(List<Search> search) throws DaoException;

    Map<String, SearchType> getSearchField();
}