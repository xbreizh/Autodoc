package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.provider.Address;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class AddressDaoImpl extends AbstractHibernateDao implements AddressDao {
    private Class<?> cl = Address.class;

    public Class<?> getClazz() {
        return cl;
    }


}
