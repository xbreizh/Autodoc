package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.contract.GlobalService;

import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {

    GlobalService service;

    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
    }

    public T getById(String token, int id){
        return (T) service.getById(token, id);
    }

    public T getByName(String token, String name){
        return (T) service.getByName(token, name);
    }

    public List<T> getAll(String token){
        return (List<T>) service.getAll(token);
    }

    public void add(Object obj){

    }

    public void update(Object obj){

    }

    public void delete(int id){

    }

}
