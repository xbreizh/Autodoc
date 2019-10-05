package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AddressDaoImpl<T extends Serializable> extends AbstractHibernateDao implements AddressDao {
    private Logger logger = Logger.getLogger(AddressDaoImpl.class);

    public AddressDaoImpl() {
        this.setClazz(Client.class);
    }


}
