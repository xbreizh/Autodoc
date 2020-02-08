package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import com.autodoc.model.models.search.SearchDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Component
public abstract class AbstractGenericManager<T, D> implements IGenericManager<T, D> {

    protected String exception = "";
    private static final Logger LOGGER = Logger.getLogger(AbstractGenericManager.class);
    private IGenericDao<T> dao;

    String[] globalComparator = {"equals", "notEquals"};
    String[] numberComparator = {"smaller", "bigger", "smallerOrEqual", };

    public AbstractGenericManager(IGenericDao dao) {
        this.dao = dao;

    }

    public AbstractGenericManager() {
    }

    protected void resetException() {
        exception = "";
    }


    public String save(D object) throws InvalidDtoException {
        LOGGER.info("trying to save: " + object.getClass());
        try {
            T objectToSave = transferInsert(object);
            LOGGER.info("object to save: " + objectToSave);
            if (!exception.isEmpty()) {
                LOGGER.error(exception);
                return exception;
            }
            String feedback = Integer.toString(dao.create(objectToSave));
            LOGGER.info("feedback: " + feedback);
            if (!feedback.equals("0")) {
                LOGGER.info("feedback: " + feedback);
                return feedback;
            }
            LOGGER.info("issue while saving");
            return "issue while saving";
        } catch (InvalidDtoException e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }

    }

    public T transferInsert(D obj) throws Exception {
        LOGGER.info("here now");
        return dtoToEntity(obj);
    }

    @Override
    public boolean update(Object entity) throws Exception {

        T obj = transferUpdate((D) entity);
        return dao.update(obj);

    }

    public T transferUpdate(D obj) throws Exception {
        LOGGER.info("transfer update generic");
        return dtoToEntity(obj);
    }


    @Override
    public void checkDataInsert(Object dto) throws Exception {
        LOGGER.info("checking insert data");
    }

    @Override
    public void checkDataUpdate(Object dto) throws InvalidDtoException {
        LOGGER.info("checking update data");
    }


    @Override
    public D getById(int id) throws Exception {
        if (dao.getById(id) == null) {
            exception = "no record found by id: " + id;
            return null;
        }
        return entityToDto(dao.getById(id));
    }

    @Override
    public D getByName(String name) throws Exception {
        if (dao.getByName(name) == null) {
            exception = "no record found by name: " + name;
            return null;
        }
        LOGGER.info("record found!! ");
        return entityToDto(dao.getByName(name));
    }

    @Override
    public List getAll() {
        LOGGER.info("getting ghtm all: ");
        LOGGER.info("trying to find them all");
        LOGGER.debug("dao: " + dao);
        return convertList(dao.getAll());
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
    public boolean delete(Object entity) throws Exception {
        LOGGER.info("trying to delete " + entity.toString());
        T obj = dtoToEntity((D) entity);
        return dao.delete((T) entity);

    }

    @Override
    public boolean deleteById(int entityId) throws Exception {
        T entity = dao.getById(entityId);
        if (entity == null) throw new Exception("id is invalid: " + entityId);
        LOGGER.info("deleting by uid manager");

        return dao.deleteById(entityId);
    }

    public List<D> searchByCriteria(List<SearchDTO> dtoList) throws Exception {
        List<Search> search = convertDtoIntoSearch(dtoList);
        return convertList(dao.getByCriteria(search));
    }

    private List<Search> convertDtoIntoSearch(List<SearchDTO> dtoList) throws Exception {
        Map<String, SearchType> authorizedList=dao.getSearchField();
        if(authorizedList==null)throw new Exception("no criteria available");
        List<Search> searchList = new ArrayList<>();
        for (SearchDTO dto:dtoList){
            boolean found=false;
            String field = dto.getFieldName();
            String compare = dto.getCompare().toUpperCase();
            String value = dto.getValue().toUpperCase();
            dto.setFieldName(field);
            Iterator it = authorizedList.entrySet().iterator();

            // checks if the field passed is in the authorized list
            // if so, the field takes the key value (for avoiding case sensitive issue on sql request)
            // and found turns gets "true" value
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String keyName = pair.getKey().toString();
                if (keyName.equalsIgnoreCase(field)){
                    field=keyName;
                    found=true;
                }
            }
            if (!found)throw new Exception(field+" is an invalid search criteria");
            String type = authorizedList.get(field).toString();
            if(!isCompareCriteria(type, dto))throw new Exception(compare+" is invalid or can't be used with "+field);
            if(type.equals("INTEGER")) {
                if(!IsValidNumber(value))throw new Exception(value+" is not a valid number");
            }
            if(type.equals("DATE"))checkDateValue(value);
            if(type.equals("STRING"))value = checkAndAdaptValue(compare, value);
            Search search = new Search(field, compare, value);
            search.setCompare(replaceCompareValueWithSqlCompare(type, compare));
            searchList.add(search);
        }

        return searchList;
    }

    private String checkAndAdaptValue(String compare, String value) throws Exception {
        if (value==null || value.isEmpty())throw new Exception("value cannot be null");
        String[] containsValues={"CONTAINS", "DOESNOTCONTAIN"};
        if (Arrays.asList(containsValues).contains(compare))value="%"+value+"%";
        return value;

    }

    private String replaceCompareValueWithSqlCompare(String type, String compare) throws Exception {
        LOGGER.info("compare: " + compare);
        for (SearchType searchType: SearchType.values()){
            if (searchType.name().equals(type)){
                for (String[] str:searchType.getValues()) {
                    if (str[0].equalsIgnoreCase(compare)) {
                        return str[1];
                    }
                }
            }
        }
        throw new Exception("something went wrong with replacing the compare");
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
            LOGGER.error("invalid date: "+dateStr);
            return false;
        }
        return true;
    }

    private boolean IsValidNumber(String value) {
        LOGGER.info("test");
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException nfe) {
            LOGGER.info("oh");
            return false;
        }
        return true;
    }




}
