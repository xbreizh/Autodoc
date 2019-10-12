package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.SubTaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.tasks.SubTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SubTaskDaoImpl<T> extends AbstractHibernateDao implements SubTaskDao {
    private Logger logger = Logger.getLogger(SubTaskDaoImpl.class);


    public SubTaskDaoImpl() {
        System.out.println("creating manuf dao");
        this.setClazz(SubTask.class);
    }


}
