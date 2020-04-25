package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import com.autodoc.model.models.search.SearchDTO;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Component
public abstract class AbstractGenericManager<T, D> implements IGenericManager<T, D> {
    private static final Logger LOGGER = Logger.getLogger(AbstractGenericManager.class);
    // protected String exception = "";
/*
    protected void resetException() {
        exception = "";
    }*/

    public IGenericDao getDao() {
        return null;
    }


    public String save(D object) {
        LOGGER.info("trying to save: " + object.getClass());
        IGenericDao dao = getDao();
        T objectToSave = transferInsert(object);
        LOGGER.info("object to save: " + objectToSave);
          /*  if (!exception.isEmpty()) {
                LOGGER.error(exception);
                return exception;
            }*/
        String feedback = Integer.toString(dao.create(objectToSave));
        LOGGER.info("feedback: " + feedback);
        if (!feedback.equals("0")) {
            LOGGER.info("feedback: " + feedback);
            return feedback;
        }
        LOGGER.info("issue while saving");
        return "issue while saving";


    }

    public T transferInsert(D obj) {
        LOGGER.info("here now");
        checkIfDuplicate(obj);
        return dtoToEntity(obj);
    }


    public T dtoToEntity(D entity) {
        if (entity == null) return null;
        return (T) new ModelMapper().map(entity, getEntityClass());

    }

    public D entityToDto(T entity) {
        if (entity == null) return null;
        return (D) new ModelMapper().map(entity, getDtoClass());
    }

    public Class getEntityClass() {
        return null;
    }

    public Class getDtoClass() {
        return null;
    }


    @Override
    public boolean update(Object entity) {

        T obj = transferUpdate((D) entity);
        boolean feedback = getDao().update(obj);
        LOGGER.info("feedback: " + feedback);
        return feedback;

    }

    public T transferUpdate(D obj) {
        LOGGER.info("transfer update generic");
        return dtoToEntity(obj);
    }


    @Override
    public void checkIfDuplicate(D obj) {
        LOGGER.info("checking insert data: " + obj);
    }

    @Override
    public void checkDataUpdate(D dto) {
        LOGGER.info("checking update data");
    }


    @Override
    public D getById(int id) {
        if (getDao().getById(id) == null) {
            throw new EntityNotFoundException("invalid id: " + id);

        }
        return entityToDto((T) getDao().getById(id));
    }

    @Override
    public D getByName(String name) {
        if (getDao().getByName(name) == null) {
            throw new InvalidDtoException("no record found by name: " + name);
        }
        LOGGER.info("record found!! ");
        return entityToDto((T) getDao().getByName(name));
    }

    @Override
    public List<D> getAll() {
        LOGGER.info("getting  all: ");
        return convertList(getDao().getAll());
    }

    protected List<D> convertList(List<T> list) {
        List<D> newList = new ArrayList<>();
        for (T obj : list) {
            LOGGER.info("here");
            Object newObj = entityToDto(obj);
            newList.add((D) newObj);
        }
        return newList;
    }


    @Override
    public boolean delete(Object entity) {
        LOGGER.info("trying to delete " + entity.toString());
        return getDao().delete(entity);

    }

    @Override
    public boolean deleteById(int entityId) {
        T entity = (T) getDao().getById(entityId);
        if (entity == null) throw new InvalidDtoException("id is invalid: " + entityId);
        LOGGER.info("deleting by uid manager");

        return getDao().deleteById(entityId);
    }

    public List<D> searchByCriteria(List<SearchDTO> dtoList) {
        List<Search> search = convertDtoIntoSearch(dtoList);
        return convertList(getDao().getByCriteria(search));
    }

    private List<Search> convertDtoIntoSearch(List<SearchDTO> dtoList) {
        Map<String, SearchType> authorizedList = getDao().getSearchField();
        if (authorizedList == null) throw new InvalidDtoException("no criteria available");
        List<Search> searchList = new ArrayList<>();
        for (SearchDTO dto : dtoList) {
            boolean found = false;
            String field = dto.getFieldName();
            String compare = dto.getCompare().toUpperCase();
            String value = dto.getValue().toUpperCase();
            dto.setFieldName(field);
            Iterator it = authorizedList.entrySet().iterator();

            // checks if the field passed is in the authorized list
            // if so, the field takes the key value (for avoiding case sensitive issue on sql request)
            // and found turns gets "true" value
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String keyName = pair.getKey().toString();
                if (keyName.equalsIgnoreCase(field)) {
                    field = keyName;
                    found = true;
                }
            }
            if (!found) throw new InvalidDtoException(field + " is an invalid search criteria");
            String type = authorizedList.get(field).toString();
            if (!isCompareCriteria(type, dto))
                throw new InvalidDtoException(compare + " is invalid or can't be used with " + field);
            if (type.equals("INTEGER")) {
                if (!isValidNumber(value)) throw new InvalidDtoException(value + " is not a valid number");
            }
            if (type.equals("DATE")) checkDateValue(value);
            if (type.equals("STRING")) value = checkAndAdaptValue(compare, value);
            Search search = new Search(field, compare, value);
            search.setCompare(replaceCompareValueWithSqlCompare(type, compare));
            searchList.add(search);
        }

        return searchList;
    }

    private String checkAndAdaptValue(String compare, String value) {
        if (value == null || value.isEmpty()) throw new InvalidDtoException("value cannot be null");
        String[] containsValues = {"CONTAINS", "DOESNOTCONTAIN"};
        if (Arrays.asList(containsValues).contains(compare)) value = "%" + value + "%";
        return value;

    }

    private String replaceCompareValueWithSqlCompare(String type, String compare) {
        LOGGER.info("compare: " + compare);
        for (SearchType searchType : SearchType.values()) {
            if (searchType.name().equals(type)) {
                for (String[] str : searchType.getValues()) {
                    if (str[0].equalsIgnoreCase(compare)) {
                        return str[1];
                    }
                }
            }
        }
        throw new InvalidDtoException("something went wrong with replacing the compare");
    }

    boolean isCompareCriteria(String type, SearchDTO dto) {
        LOGGER.info("type: " + type);
        LOGGER.info("dto: " + dto);
        int found = 0;
        for (SearchType searchType : SearchType.values()) {
            if (searchType.name().equals(type)) {
                for (String[] str : searchType.getValues()) {
                    if (str[0].equalsIgnoreCase(dto.getCompare())) {
                        dto.setCompare(str[1]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDateValue(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("invalid date: " + dateStr);
            return false;
        }
        return true;
    }

    private boolean isValidNumber(String value) {
        LOGGER.debug("checking if a valid number");
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }


}
