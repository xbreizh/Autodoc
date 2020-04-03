package com.autodoc.dao.impl.person.client;

import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class ClientDaoImpl<T> extends AbstractHibernateDao implements ClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDaoImpl.class);
    private Class<?> cl = Client.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {

        return Client.getSearchField();
    }


    @Override
    public Client getByName(String lastName) {
        TypedQuery<Client> query = getCurrentSession().createQuery(FROM + "Client where lastName= :lastName");
        query.setParameter("lastName", lastName.toUpperCase());
        List<Client> clients = query.getResultList();
        LOGGER.debug("found: " + clients.size());
        LOGGER.info("size in dao: " + clients.size());
        if (!clients.isEmpty()) return clients.get(0);
        return null;
    }

}
