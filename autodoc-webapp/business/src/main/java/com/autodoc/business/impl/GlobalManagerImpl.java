package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.business.exceptions.ObjectFormattingException;
import com.autodoc.contract.EnumService;
import com.autodoc.contract.GlobalService;
import com.autodoc.impl.EnumServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {
    protected static final double PRICE_PER_HOUR = 15;
    protected static final double VAT = 19.5;
    private static final Logger LOGGER = Logger.getLogger(GlobalManagerImpl.class);
    //  GlobalService service;
    EnumService enumService = new EnumServiceImpl();

    protected SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm");
    }

    GlobalService getService() {
        return null;
    }

/*    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
        if (enumService == null) enumService = new EnumServiceImpl();
    }*/

    public double getPricePerHour() {
        return PRICE_PER_HOUR;
    }

    public double getVat() {
        return VAT;
    }

    public T getById(String token, int id) throws Exception {
        GlobalService service = getService();
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
        GlobalService service = getService();
        D obj = (D) service.getByName(token, name);
        return dtoToEntity(token, obj);
    }

    public T dtoToEntity(String token, Object obj) throws Exception {
        LOGGER.info("converting into entito");
        return null;
    }


    public List<T> getAll(String token) throws Exception {
        GlobalService service = getService();
        return convertList(token, service.getAll(token));
    }

    public String add(String token, Object obj) throws Exception {
        LOGGER.info("stuff to insert: " + obj);
        GlobalService service = getService();
        D objToInsert = formToDto(obj, token);
        String feedBack = "";
        int code = 0;
        try {
            feedBack = service.create(token, objToInsert);
            LOGGER.info("feedback: " + feedBack);
            code = getRequestCode(feedBack);
        } catch (Exception e) {
            LOGGER.error("exception found: " + e.getMessage());
            HttpStatus status = HttpStatus.valueOf(getRequestCode(feedBack));
            throw new HttpClientErrorException(status, getFeedbackDetails(feedBack));
        }
        LOGGER.info("code: " + code);
        if (code != 201) {
            throw new ObjectFormattingException(getFeedbackDetails(feedBack));
        }
        return getFeedbackDetails(feedBack);
    }

   /* private String returnFeedback(int codeExpected, String feedback) {
        if (getRequestCode(feedback) != codeExpected) return getFeedbackDetails(feedback);
        return feedback;
    }*/

    protected String getFeedbackDetails(String feedback) {
        LOGGER.info("feedback: " + feedback);
        try {
            int start = feedback.indexOf("/ ");
            String details = feedback.substring(start + 2);
            LOGGER.info("details: " + details);
            return details;
        } catch (Exception e) {
            LOGGER.error("issue while getting feedback details: " + e.getStackTrace());
            LOGGER.error(feedback);
        }
        return "an error occurred. Please check the logs";
    }

    public String update(String token, Object obj) throws Exception {
        LOGGER.info("stuff to update: " + obj);
        GlobalService service = getService();
        D objToUpdate = formToDto(obj, token);
        String feedBack = "";
        int code = 0;
        try {
            feedBack = service.update(token, objToUpdate);
            LOGGER.info("feedback: " + feedBack);
            code = getRequestCode(feedBack);
        } catch (Exception e) {
            LOGGER.error("exception found: " + e.getMessage());
            HttpStatus status = HttpStatus.valueOf(getRequestCode(feedBack));
            throw new HttpClientErrorException(status, getFeedbackDetails(feedBack));
        }
        LOGGER.info("code: " + code);
        if (code != 200) {
            LOGGER.error("exception: " + feedBack);
            throw new ObjectFormattingException(getFeedbackDetails(feedBack));
        }
        return getFeedbackDetails(feedBack);

    }

    public D formToDto(Object obj, String token) throws Exception {
        LOGGER.error("not configured");
        return null;
    }

    public void delete(String token, int id) {
        GlobalService service = getService();
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
        LOGGER.info("feedback: " + feedback);
        String code = feedback.substring(0, 3);
        try {
            return Integer.parseInt(code);

        } catch (NumberFormatException e) {
            LOGGER.error("invalid code received: " + code);
        }
        return 999;
    }

    @Override
    public void checkIfDateIsValid(String stringDate) throws Exception {
        if (stringDate == null) {
            throw new ObjectFormattingException("date shouldn't be null");
        }
        System.out.println("reaching: " + stringDate);
        System.out.println("expected format: " + getDateFormat().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        sdf.setLenient(false);
        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(stringDate);
            System.out.println(date);

        } catch (ParseException e) {

            throw new ObjectFormattingException("invalid date: " + stringDate);
        }

    }


}
