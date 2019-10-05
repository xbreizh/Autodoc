package com.autodoc.dao.impl;

import com.autodoc.dao.contract.ClientDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ClientDaoImpl<T extends Serializable> extends AbstractHibernateDao implements ClientDao {
    private Logger logger = Logger.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl() {
        this.setClazz(Client.class);
    }


}
