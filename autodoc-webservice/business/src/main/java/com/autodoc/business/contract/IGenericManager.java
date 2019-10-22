package com.autodoc.business.contract;

import java.util.List;

public interface IGenericManager<T, D> {


    D getById(final int id);

    List<D> getAll();

    String save(final D entity) throws Exception;

    String update(final D entity);

    String delete(final D entity);

    String deleteById(final int entityId);

    D entityToDto(final T entity);

    T dtoToEntity(final D entity);
}
