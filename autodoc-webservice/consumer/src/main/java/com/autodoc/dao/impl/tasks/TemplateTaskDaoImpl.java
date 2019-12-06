/*
package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TemplateTaskDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.tasks.TemplateTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TemplateTaskDaoImpl<T> extends AbstractHibernateDao implements TemplateTaskDao {
    private static final Logger LOGGER = Logger.getLogger(TemplateTaskDaoImpl.class);


    public TemplateTaskDaoImpl() {
        this.setClazz(TemplateTask.class);
    }

    public Map<String, SearchType> getSearchField() {

        return  TemplateTask.SEARCH_FIELD;
    }


}
*/
