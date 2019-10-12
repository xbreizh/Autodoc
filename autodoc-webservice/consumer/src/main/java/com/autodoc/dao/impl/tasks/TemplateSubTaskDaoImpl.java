package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TemplateSubTaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TemplateSubTaskDaoImpl<T> extends AbstractHibernateDao implements TemplateSubTaskDao {
    private Logger logger = Logger.getLogger(TemplateSubTaskDaoImpl.class);


    public TemplateSubTaskDaoImpl() {
        System.out.println("creating manuf dao");
        this.setClazz(TemplateSubTask.class);
    }


}
