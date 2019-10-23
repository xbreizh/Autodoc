package com.autodoc.business.contract;

import java.util.List;

public interface IGenericManager<T, D> {


    D getById(final int id) throws Exception;

    List<D> getAll();

    String save(final D entity) throws Exception;

    D update(final D entity) throws Exception;

    String delete(final D entity);

    String deleteById(final int entityId);

    D entityToDto(final T entity);

    T dtoToEntity(final D entity) throws Exception;
}
