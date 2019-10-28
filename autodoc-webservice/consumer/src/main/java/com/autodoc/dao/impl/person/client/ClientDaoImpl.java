package com.autodoc.dao.impl.person.client;

import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ClientDaoImpl extends AbstractHibernateDao implements ClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl() {
        this.setClazz(Client.class);
    }


    @Override
    public Client getByName(String name) {
        return null;
    }

}
