package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TaskDaoImpl<T> extends AbstractHibernateDao implements TaskDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDaoImpl.class);


    public TaskDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(Task.class);
    }

    public Map<String, SearchType> getSearchField() {

        return Task.SEARCH_FIELD;
    }


    @Override
    public Task getByName(String name) {
        System.out.println("get task by name: " + name);
        Query query = getCurrentSession().createQuery("From Task where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return (Task) query.getResultList().get(0);
    }

}
