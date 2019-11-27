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
        System.out.println("getting by id");
        System.out.println(service);
        D obj = (D)  service.getById(token, id);
        System.out.println("className: "+obj.getClass().getName());
        T cc = dtoToEntity(obj);
        System.out.println("className: "+cc.getClass().getName());
        return dtoToEntity(obj);
    }

    public T getByName(String token, String name){

        D obj = (D) service.getByName(token, name);
        return dtoToEntity(obj);
    }

    public T dtoToEntity(Object obj) {

        return null;
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
