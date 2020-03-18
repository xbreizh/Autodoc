package com.autodoc.dao.impl.global;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractHibernateDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);
    @Inject
    SessionFactory sessionFactory;
    //  int maxCharacters = 22;
    private Class<T> clazz;
    //private String dateFormat;

   /* public static boolean isValidNumber(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }*/

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;

    }

/*    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    public T getById(int id) throws ObjectNotFoundException {
        final Serializable entityId = id;
        LOGGER.info("trying to get object by id: " + id);
        Object obj;
        try {
            obj = getCurrentSession().load(clazz, entityId);
            // sysout setup to provoke the ObjectNotFoundException if required
            System.out.println(obj);
        } catch (ObjectNotFoundException nf) {
            return null;
        }
        return (T) obj;
    }


    public T getByName(String name) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName() + "where name = :name");
        query.setParameter(name, name);
        if (query.getResultList().isEmpty()) return null;
        return (T) query.getResultList().get(0);
    }

    public List<T> getAll() {
        LOGGER.info("in the dao: " + clazz.getName());
        LOGGER.debug("class: " + clazz.getName());
        LOGGER.debug("getting all");
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    public int create(T entity) {
        LOGGER.info("trying to create: " + entity);
        try {
            return (Integer) getCurrentSession().save(entity);
        } catch (Exception e) {
            LOGGER.error("error while creating");
            return 0;
        }
    }

    public boolean delete(T entity) {
        LOGGER.info("I want to delete: " + entity);
        getCurrentSession().delete(entity);
        return true;

    }

    public boolean update(T entity) {
        LOGGER.info("updating from dao: " + entity);
        T obj = (T) getCurrentSession().merge(entity);
        return obj != null;
    }

    public boolean deleteById(int entityId) {
        LOGGER.info("trying to delete "+clazz+" with id: " + entityId);
        T entity = getCurrentSession().get(clazz, entityId);
        LOGGER.info(entity);
        if (entity == null) {
            LOGGER.info("entity not found");
            return false;
        }
        delete(entity);
       // return getById(entityId) == null;
        return true;
    }

    protected Session getCurrentSession() {
        LOGGER.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

    public List<T> getByCriteria(List<Search> search) throws Exception {
        if (search == null) throw new Exception("no search criteria provided");
        if (getSearchField() == null) throw new Exception("no search criteria available for that entity");
        String request = buildCriteriaRequest(search);
        LOGGER.info("req: " + request);
        Query query = sessionFactory.getCurrentSession().createQuery(request);
        return query.getResultList();
    }

    protected String buildCriteriaRequest(List<Search> searchList) throws Exception {

        StringBuilder sb = new StringBuilder();
        String init = "from " + clazz.getSimpleName();
        sb.append(init);
        for (Search search : searchList) {
            if (sb.toString().equals(init)) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(search.getFieldName() + " " + search.getCompare() + " '" + search.getValue() + "'");

        }
        return sb.toString();


    }

/*    private void checkIfInvalidValue(Compare compare, String value) throws Exception {
        String type = compare.getType();
        if (type.equals("Integer")) {
            if (!isValidNumber(value)) throw new Exception(value + " is not a number");
        } else if (type.equals("Date")) {
            if (!isValidDate(value)) throw new Exception(value + "is not a date");
        }
    }

    private void checkIfInvalidField(Map<String, SearchType> authorizedSearchFieldList, Search s) throws Exception {
        if (!authorizedSearchFieldList.containsKey(s.getFieldName())) {
            throw new Exception("invalid criteria: " + s.getFieldName());
        }
    }*/

 /*   public boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        } catch (java.text.ParseException e) {
            return false;
        }
        return true;
    }*/


    public Map<String, SearchType> getSearchField() {

        return null;
    }

}
