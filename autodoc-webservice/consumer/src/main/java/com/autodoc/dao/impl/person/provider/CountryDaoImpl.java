package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class CountryDaoImpl extends AbstractHibernateDao implements CountryDao {
    private static final Logger LOGGER = Logger.getLogger(CountryDaoImpl.class);
    private Class<?> cl = Country.class;

    public Class<?> getClazz() {
        return cl;
    }


    public Map<String, SearchType> getSearchField() {

        return Country.getSearchField();
    }

    @Override
    public Country getByName(String name) {
        LOGGER.info("get country by name: " + name);
        TypedQuery<Country> query = getCurrentSession().createQuery(FROM + " Country where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return query.getResultList().get(0);
    }


}
