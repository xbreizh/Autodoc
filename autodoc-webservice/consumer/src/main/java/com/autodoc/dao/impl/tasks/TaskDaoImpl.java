package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TaskDaoImpl<T> extends AbstractHibernateDao implements TaskDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDaoImpl.class);


    public TaskDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(Task.class);
    }


}
