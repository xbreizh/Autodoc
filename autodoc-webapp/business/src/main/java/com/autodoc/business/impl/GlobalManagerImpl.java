package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {
    private static Logger LOGGER = Logger.getLogger(GlobalManagerImpl.class);
    GlobalService service;

    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
    }

    public T getById(String token, int id) throws Exception {
        LOGGER.info("getting by id");
        LOGGER.info(service);
        D obj = (D)  service.getById(token, id);
        if (obj == null) return null;
        LOGGER.info("className: "+obj.getClass().getName());
        T cc = dtoToEntity(token, obj);
        LOGGER.info("object: " + cc);
        return dtoToEntity(token, obj);
    }

    public T getByName(String token, String name) throws Exception {

        D obj = (D) service.getByName(token, name);
        return dtoToEntity(token, obj);
    }

    public T dtoToEntity(String token, Object obj) throws Exception {
        LOGGER.info("converting into entito");
        return null;
    }


    public List<T> getAll(String token) throws Exception {
        return  convertList(token, service.getAll(token));
    }

    public void add(String token, Object obj) {
        LOGGER.info("stuff to insert: " + obj);
        D objToInsert = formToDto(obj);
        service.create(token, objToInsert);

    }

    public void update(String token, Object obj) {
        LOGGER.info("stuff to update: " + obj);
        D objToUpdate = formToDto(obj);
        service.update(token, objToUpdate);

    }

    public D formToDto(Object obj) {
        LOGGER.error("not configured");
        return null;
    }

    public void delete(String token, int id) {
        LOGGER.info("deleting: " + id);
        service.delete(token, id);
    }

    public int getMax(String token) {

        return 0;
    }

    List<T> convertList(String token, List<D> list) throws Exception {
        LOGGER.info("converting list: "+list);
        List<T> newList = new ArrayList<>();
        for (D obj : list) {
            newList.add(dtoToEntity(token, obj));
        }
        LOGGER.info("new list: "+newList);
        return newList;
    }

}
