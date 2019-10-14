package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryDaoImpl<T> extends AbstractHibernateDao implements CountryDao {
    private Logger logger = Logger.getLogger(CountryDaoImpl.class);

    public CountryDaoImpl() {
        this.setClazz(Country.class);
    }


}
