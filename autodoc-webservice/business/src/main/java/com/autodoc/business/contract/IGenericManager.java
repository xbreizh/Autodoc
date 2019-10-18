package com.autodoc.business.contract;

import java.util.List;

public interface IGenericManager<T, D> {


    D getById(final int id);

    List<D> getAll();

    String save(final T entity);

    String update(final T entity);

    String delete(final T entity);

    String deleteById(final int entityId);

    D entityToDto(final T entity);

    //T dtoToEntity(final D dto);
}
