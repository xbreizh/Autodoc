package com.autodoc.business.contract;

import java.util.List;

public interface GlobalManager<T, D> {

    D getById(String token, int id);

    D getByName(String token, String name);

    List<D> getAll(String token);

    void add(T obj);

    void update(T obj);

    void delete(int id);


}
