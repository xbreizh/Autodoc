package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.contract.EnumService;
import com.autodoc.contract.GlobalService;
import com.autodoc.impl.EnumServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {
    protected static final double pricePerHour = 15;
    private static Logger LOGGER = Logger.getLogger(GlobalManagerImpl.class);
    GlobalService service;
    EnumService enumService;

    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
        if (enumService == null) enumService = new EnumServiceImpl();
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public T getById(String token, int id) throws Exception {
        LOGGER.info("getting by id");
        LOGGER.info(service);
        D obj = (D) service.getById(token, id);
        if (obj == null) return null;
        LOGGER.info("className: " + obj.getClass().getName());
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
        return convertList(token, service.getAll(token));
    }

    public String add(String token, Object obj) throws Exception {
        LOGGER.info("stuff to insert: " + obj);
        D objToInsert = formToDto(obj, token);
        /*String feedback = service.create(token, objToInsert);
        try {
            Integer.parseInt(feedback);
        } catch (NumberFormatException e) {
            LOGGER.error(feedback);
            throw new ObjectFormattingException(feedback)
        }
        LOGGER.info("id: " + feedback);
        return returnFeedback(201, feedback);*/
        return service.create(token, objToInsert);
       /* String feedBack = "";
        try {
            feedBack = service.update(token, objToInsert);
            return feedBack;
        } catch (RuntimeException e) {
            throw new ObjectFormattingException( getFeedbackDetails(feedBack));
        }*/

    }

    private String returnFeedback(int codeExpected, String feedback) {
        if (getRequestCode(feedback) != codeExpected) return getFeedbackDetails(feedback);
        return feedback;
    }

    protected String getFeedbackDetails(String feedback) {
        try {
            int start = feedback.indexOf("\"");
            String details = feedback.substring(start + 1, feedback.length() - 1);
            return details;
        } catch (Exception e) {
            LOGGER.error("issue while getting feedback details: " + e.getStackTrace());
            LOGGER.error(feedback);
        }
        return "an error occurred. Please check the logs";
    }

    public void update(String token, Object obj) throws Exception {
        LOGGER.info("stuff to update: " + obj);
        D objToUpdate = formToDto(obj, token);
        String feedBack = "";
        try {
            feedBack = service.update(token, objToUpdate);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.valueOf(getRequestCode(feedBack));
            throw new HttpClientErrorException(status, getFeedbackDetails(feedBack));
        }

    }

    public D formToDto(Object obj, String token) throws Exception {
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
        LOGGER.info("converting list: " + list);
        List<T> newList = new ArrayList<>();
        for (D obj : list) {
            newList.add(dtoToEntity(token, obj));
        }
        LOGGER.info("new list: " + newList);
        return newList;
    }

    int getRequestCode(String feedback) {
        String code = feedback.substring(0, 3);
        try {
            return Integer.parseInt(code);

        } catch (NumberFormatException e) {
            LOGGER.error("invalid code received: " + code);
        }
        return 999;
    }


}
