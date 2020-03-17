package com.autodoc.contract;

import com.autodoc.model.dtos.SearchDto;

import java.util.List;

public interface GlobalService<T> {

    T getById(String token, int id);


    T getByName(String token, String name);

    List<T> getAll(String token);

    List<T> getByCriteria(String token, SearchDto searchDto);

    String create(String token, T object);

    String update(String token, T object);

    int delete(String token, int id);


    String getClassName();

    void filler();
}
