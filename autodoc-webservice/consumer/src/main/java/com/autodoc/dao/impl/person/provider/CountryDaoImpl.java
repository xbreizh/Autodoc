package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryDaoImpl<T> extends AbstractHibernateDao implements CountryDao {
    private static final Logger LOGGER = Logger.getLogger(CountryDaoImpl.class);

    public CountryDaoImpl() {
        this.setClazz(Country.class);
    }


    public Map<String, SearchType> getSearchField() {

        return Country.SEARCH_FIELD;
    }

    @Override
    public Country getByName(String name) {
        System.out.println("get country by name: " + name);
        Query query = getCurrentSession().createQuery("From Country where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return (Country) query.getResultList().get(0);
    }


}
