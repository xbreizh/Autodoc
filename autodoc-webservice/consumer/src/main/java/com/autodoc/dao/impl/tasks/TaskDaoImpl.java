package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class TaskDaoImpl<T> extends AbstractHibernateDao implements TaskDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDaoImpl.class);
    private Class<?> cl = Task.class;

    public Class<?> getClazz() {
        return cl;
    }


    public Map<String, SearchType> getSearchField() {

        return Task.getSearchField();
    }


    @Override
    public Task getByName(String name) {
        LOGGER.info("get task by name: " + name);
        TypedQuery<Task> query = getCurrentSession().createQuery("From Task where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return query.getResultList().get(0);
    }


    public boolean deleteById(int id) {
        LOGGER.info("deleting tasks");
        LOGGER.info("get task by id: " + id);
        TypedQuery<Boolean> query = getCurrentSession().createQuery("delete From Task where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Object entity) {
        LOGGER.info("updating from dao: " + entity);
        T obj = (T) getCurrentSession().merge(entity);
        return obj != null;
    }

}
