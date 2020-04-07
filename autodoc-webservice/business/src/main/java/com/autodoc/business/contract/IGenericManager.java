package com.autodoc.business.contract;

import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.search.SearchDTO;

import java.util.List;

public interface IGenericManager<T, D> {

    IGenericDao getDao();

    D getById(final int id) throws InvalidDtoException;

    List<D> getAll();

    String save(final D entity) throws InvalidDtoException;

    boolean update(final D entity) throws InvalidDtoException;

    boolean delete(final D entity) throws InvalidDtoException;

    boolean deleteById(final int entityId) throws InvalidDtoException;

    D entityToDto(final T entity);

    T dtoToEntity(final D entity) throws InvalidDtoException;

    void checkIfDuplicate(D dto) throws InvalidDtoException;

    void checkDataUpdate(D dto) throws InvalidDtoException;

    D getByName(String name) throws InvalidDtoException;

    List<D> searchByCriteria(List<SearchDTO> search) throws InvalidDtoException;

    T transferInsert(D obj) throws InvalidDtoException;

    T transferUpdate(D obj) throws InvalidDtoException;

    Class getEntityClass();

    Class getDtoClass();
}
