package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.contract.GlobalService;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {
    private static Logger LOGGER = Logger.getLogger(GlobalManagerImpl.class);
    GlobalService service;

    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
    }

    public T getById(String token, int id){
        System.out.println("getting by id");
        System.out.println(service);
        D obj = (D)  service.getById(token, id);
        if (obj == null) return null;
        System.out.println("className: "+obj.getClass().getName());
        T cc = dtoToEntity(token, obj);
        LOGGER.info("object: " + cc);
        return dtoToEntity(token, obj);
    }

    public T getByName(String token, String name){

        D obj = (D) service.getByName(token, name);
        return dtoToEntity(token, obj);
    }

    public T dtoToEntity(String token, Object obj) {
        LOGGER.info("converting into entito");
        return null;
    }


    public List<T> getAll(String token){

        return (List<T>) service.getAll(token);
    }

    public void add(String token, Object obj) {

    }

    public void update(String token, Object obj) {
        LOGGER.info("stuff to update: " + obj);
        D objToUpdate = (D) obj;
        service.update(token, objToUpdate);

    }

    public void delete(String token, int id) {

    }

}
