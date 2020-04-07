package com.autodoc.business.contract;

import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.search.SearchDTO;

import java.util.List;

public interface IGenericManager<T, D> {

    IGenericDao getDao();

    D getById(final int id) throws Exception;

    List<D> getAll();

    String save(final D entity) throws InvalidDtoException;

    boolean update(final D entity) throws Exception;

    boolean delete(final D entity) throws Exception;

    boolean deleteById(final int entityId) throws Exception;

    D entityToDto(final T entity);

    T dtoToEntity(final D entity) throws Exception;

    void checkIfDuplicate(Object dto) throws Exception;

    void checkDataUpdate(Object dto) throws InvalidDtoException;

    D getByName(String name) throws Exception;

    List<D> searchByCriteria(List<SearchDTO> search) throws Exception;
}
