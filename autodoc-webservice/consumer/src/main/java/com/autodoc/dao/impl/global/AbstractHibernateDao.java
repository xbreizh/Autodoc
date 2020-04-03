package com.autodoc.dao.impl.global;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);
    protected static final String FROM = "from ";
    @Inject
    SessionFactory sessionFactory;

    public Class<?> getClazz() {
        return null;
    }


    public T getById(int id) throws ObjectNotFoundException {
        final Serializable entityId = id;
        LOGGER.info("getting object by id: " + id);
        Object obj;
        try {
            obj = getCurrentSession().load(getClazz(), entityId);
        } catch (ObjectNotFoundException nf) {
            return null;
        }
        return (T) obj;
    }


    public T getByName(String name) {
        TypedQuery<T> query = getCurrentSession().createQuery(FROM + getClazz().getName() + "where name = :name");
        query.setParameter(name, name);
        List<T> list = query.getResultList();
        if (list.isEmpty()) return null;
        return query.getResultList().get(0);
    }

    public List<T> getAll() {
        LOGGER.debug("getting all");
        return getCurrentSession().createQuery(FROM + getClazz().getName()).getResultList();
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
        LOGGER.info("trying to delete " + getClazz() + " with id: " + entityId);
        T entity = (T) getCurrentSession().get(getClazz(), entityId);
        LOGGER.info(entity);
        if (entity == null) {
            LOGGER.info("entity not found");
            return false;
        }
        delete(entity);
        return true;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<T> getByCriteria(List<Search> search) throws Exception {
        if (search == null) throw new Exception("no search criteria provided");
        if (getSearchField() == null) throw new Exception("no search criteria available for that entity");
        String request = buildCriteriaRequest(search);
        LOGGER.info("req: " + request);
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery(request);
        return query.getResultList();
    }

    protected String buildCriteriaRequest(List<Search> searchList) throws Exception {

        StringBuilder sb = new StringBuilder();
        String init = FROM + getClazz().getSimpleName();
        sb.append(init);
        for (Search search : searchList) {
            if (sb.toString().equals(init)) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(search.getFieldName());
            sb.append(" ");
            sb.append(search.getCompare());
            sb.append(" '");
            sb.append(search.getValue() + "'");

        }
        return sb.toString();


    }


    public Map<String, SearchType> getSearchField() {

        return null;
    }

}
