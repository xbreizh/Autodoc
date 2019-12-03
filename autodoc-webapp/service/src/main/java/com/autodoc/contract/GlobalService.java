package com.autodoc.contract;

import java.util.List;
import java.util.Map;

public interface GlobalService<T> {

    T getById(String token, int id);


    T getByName(String token, String name);

    List<T> getAll(String token);

    List<T> getByCriteria(String token, Map<String, String> criteria);

    void create(String token, T object);

    void update(String token, T object);

    void delete(String token, int id);


    String getClassName();
}
