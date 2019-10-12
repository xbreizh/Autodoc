package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TaskDaoImpl<T> extends AbstractHibernateDao implements TaskDao {
    private Logger logger = Logger.getLogger(TaskDaoImpl.class);


    public TaskDaoImpl() {
        System.out.println("creating manuf dao");
        this.setClazz(Task.class);
    }


}
