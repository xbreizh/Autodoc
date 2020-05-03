package com.autodoc.dao.impl.global;

import com.autodoc.dao.exceptions.DaoException;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T> {
    protected static final String FROM = "from ";
    protected static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);
    Session session;
    @Inject
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<?> getClazz() {
        return null;
    }

    public T getById(int id) {
        final Serializable entityId = id;
        LOGGER.info("getting object by id: " + id);
        if (getClazz() == null) return null;
        return (T) getCurrentSession().get(getClazz(), entityId);
    }


    public T getByName(String name) {
        return null;
    }

    public List<T> getAll() {
        LOGGER.debug("getting all");
        StringBuilder request = new StringBuilder();
        request.append(FROM);
        request.append(getClazz().getName());
        TypedQuery<T> query = getCurrentSession().createQuery(request.toString());
        return query.getResultList();
    }

    public int create(T entity) {
        LOGGER.info("trying to create: " + entity);
        return (Integer) getCurrentSession().save(entity);
    }


    public boolean delete(T entity) {
        LOGGER.info("I want to delete: " + entity);
      /*  Transaction transaction;
        transaction = getCurrentSession().beginTransaction();*/
        getCurrentSession().update(entity);
        try {
            getCurrentSession().remove(entity);
            getCurrentSession().flush();

        } catch (PersistenceException exception) {
            LOGGER.error("returning false");
           // transaction.rollback();
            return false;
        }
      //  transaction.commit();
        return true;

    }

    @Transactional(propagation = REQUIRES_NEW)
    public boolean update(T entity) {
        LOGGER.info("updating from dao: " + entity);
        getCurrentSession().merge(entity);
        return true;

    }

    public boolean deleteById(int entityId) {
        T entity = (T) getCurrentSession().get(getClazz(), entityId);
        LOGGER.info(entity);
        if (entity == null) {
            LOGGER.info("entity not found");
            return false;
        }

        return delete(entity);
    }

    public Session getCurrentSession() {
        session = sessionFactory.getCurrentSession();
        LOGGER.info("getting session: " + session);

        return session;
    }

    public List<T> getByCriteria(List<Search> search) throws DaoException {
        if (search == null) throw new DaoException("no search criteria provided");
        if (getSearchField() == null) throw new DaoException("no search criteria available for that entity");
        String request = buildCriteriaRequest(search);
        LOGGER.info("req: " + request);
        Query query = sessionFactory.getCurrentSession().createQuery(request);
        return query.getResultList();
    }

    protected String buildCriteriaRequest(List<Search> searchList) {
        StringBuilder sb = new StringBuilder();
        String init = FROM + getClazz().getSimpleName();
        LOGGER.info("init: " + init);
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
