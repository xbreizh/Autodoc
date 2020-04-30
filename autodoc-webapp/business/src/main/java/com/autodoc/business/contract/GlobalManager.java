package com.autodoc.business.contract;

import java.util.List;

public interface GlobalManager<T, D> {

    D getById(String token, int id) throws Exception;

    D getByName(String token, String name) throws Exception;

    List<D> getAll(String token) throws Exception;

    String add(String token, T obj) throws Exception;

    String update(String token, T obj) throws Exception;

    void delete(String token, int id);

    double getPricePerHour();

    double getVat();

    void checkIfDateIsValid(String stringDate) throws Exception;


}
