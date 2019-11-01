package com.autodoc.dao.impl.global;

import com.autodoc.model.enums.Compare;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.expression.ParseException;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public abstract class AbstractHibernateDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);
    @Inject
    SessionFactory sessionFactory;
    private Class<T> clazz;
    private String dateFormat;
    int maxCharacters=22;



    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T getById(int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        System.out.println("in the dao: " + clazz.getName());
        LOGGER.debug("class: " + clazz.getName());
        LOGGER.debug("getting all");
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    public int create(T entity) {
        try {
            return (Integer) getCurrentSession().save(entity);
        } catch (Exception e) {
            return 0;
        }
    }

    public String delete(T entity) {
        getCurrentSession().delete(entity);
        return "";

    }

    public String update(T entity) {
        T obj = (T) getCurrentSession().merge(entity);
        if (obj != null) return "updated";
        return null;
    }

    //@Override
    public T getByName(String name) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName() + "where name = :name");
        query.setParameter(name, name);
        if (query.getResultList().isEmpty()) return null;
        return (T) query.getResultList().get(0);
    }

    public String deleteById(final int entityId) {
        T entity = getById(entityId);
        delete(entity);
        return "";
    }

    protected Session getCurrentSession() {
        LOGGER.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

    public List<T> getByCriteria(List<Search> search) throws Exception {
        if (search == null) throw new Exception("no search criteria provided");
        if(getSearchField()==null)throw new Exception("no search criteria available for that entity");
        String request = buildCriteriaRequest(search);
        System.out.println("req: "+request);
        Query query = sessionFactory.getCurrentSession().createQuery(request);
        return query.getResultList();
    }


    protected String buildCriteriaRequest(List<Search> searchList) throws Exception {

        StringBuilder sb = new StringBuilder();
        String init="from "+clazz.getSimpleName();
        sb.append(init);
        Search search = searchList.get(0);
        //String type="";













        sb.append(" where "+search.getFieldName()+" "+search.getCompare()+" '"+search.getValue()+"'");
        return sb.toString();



       /* StringBuilder builder = new StringBuilder();
        String init="from "+clazz.getSimpleName();
        Map<String, SearchType> authorizedSearchFieldList = getSearchField();

        if (authorizedSearchFieldList == null) return null;

        builder.append(init);

        for (Search s : searchList) {
            if (s.getValue()==null||s.getValue().isEmpty())throw new Exception(s.getFieldName()+"cannot be null");
            if(s.getValue().length() > maxCharacters)throw new Exception(s.getValue()+"cannot is more than the authorized "+maxCharacters);
            s.setValue(s.getValue().toUpperCase());
            checkIfInvalidField(authorizedSearchFieldList, s);
            checkIfInvalidValue(s.getCompare(), s.getValue());
            System.out.println("old val: "+s.getValue());
            if(s.getCompare() ==Compare.STRINGCONTAINS || s.getCompare()==Compare.STRINGDOESNOTCONTAIN)s.setValue("\'%"+s.getValue()+"%\'");
            System.out.println("new val: "+s.getValue());
            if (builder.toString().equals(init)){
                builder.append(" where ");
            }else {
                builder.append(" and ");
            }
            builder.append(s.getFieldName()+s.getCompare().getQueryValue()+" "+s.getValue()+" ");
        }
        System.out.println("query: "+builder.toString());
        LOGGER.info("query build: "+builder.toString());

        return builder.toString();*/
       //return null;
    }



    private void checkIfInvalidValue(Compare compare, String value) throws Exception {
        String type = compare.getType();
        if (type.equals("Integer")) {
            if (!isValidNumber(value)) throw new Exception(value + " is not a number");
        } else if(type.equals("Date")){
            if (!isValidDate(value)) throw new Exception(value + "is not a date");
        }
    }

    private void checkIfInvalidField(Map<String, SearchType> authorizedSearchFieldList, Search s) throws Exception {
        if (!authorizedSearchFieldList.containsKey(s.getFieldName())) {
            throw new Exception("invalid criteria: " + s.getFieldName());
        }
    }

    public static boolean isValidNumber(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public boolean isValidDate(String dateStr) {
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
    }


    public Map<String, SearchType> getSearchField() {

        return null;
    }

}
