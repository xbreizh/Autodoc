package com.autodoc.business.contract;

import java.util.List;

public interface GlobalManager<T, D> {

    D getById(String token, int id) throws Exception;

    D getByName(String token, String name) throws Exception;

    List<D> getAll(String token) throws Exception;

    void add(String token, T obj) throws Exception;

    void update(String token, T obj) throws Exception;

    void delete(String token, int id);


}
