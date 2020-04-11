package com.autodoc.business.contract;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.search.SearchDTO;

import java.util.List;

public interface IGenericManager<T, D> {

    IGenericDao getDao();

    D getById(final int id);

    List<D> getAll();

    String save(final D entity);

    boolean update(final D entity);

    boolean delete(final D entity);

    boolean deleteById(final int entityId);

    D entityToDto(final T entity);

    T dtoToEntity(final D entity);

    void checkIfDuplicate(D dto);

    void checkDataUpdate(D dto);

    D getByName(String name);

    List<D> searchByCriteria(List<SearchDTO> search);

    T transferInsert(D obj);

    T transferUpdate(D obj);

    Class getEntityClass();

    Class getDtoClass();
}
