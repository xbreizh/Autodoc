package com.autodoc.business.contract;

import java.util.List;

public interface GlobalManager<T> {

    T getById(String token, int id);

    T getByName(String token, String name);

    List<T> getAll(String token);

    void add(T obj);

    void update(T obj);

    void delete(int id);


}
