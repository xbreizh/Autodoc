/*
package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.SkillDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.employee.Skill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SkillDaoImpl<T> extends AbstractHibernateDao implements SkillDao {
    private static final Logger LOGGER = Logger.getLogger(SkillDaoImpl.class);

    public SkillDaoImpl() {
        this.setClazz(Skill.class);
    }

    public Map<String, SearchType> getSearchField() {

        return  Skill.SEARCH_FIELD;
    }
}
*/
