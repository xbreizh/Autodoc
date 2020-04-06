package com.autodoc.dao.impl.global;

import com.autodoc.dao.contract.global.IGenericDao;
import lombok.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Generated
public class GenericHibernateDao<T>
        extends AbstractHibernateDao<T> implements IGenericDao<T> {


}