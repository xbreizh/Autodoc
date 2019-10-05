package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.SkillDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SkillDaoImpl<T extends Serializable> extends AbstractHibernateDao implements SkillDao {
    private Logger logger = Logger.getLogger(SkillDaoImpl.class);

    public SkillDaoImpl() {
        this.setClazz(Client.class);
    }


}
