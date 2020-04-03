package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.provider.Provider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProviderDaoImpl extends AbstractHibernateDao implements ProviderDao {
    private Class<?> cl = Provider.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {

        return Provider.getSearchField();
    }

}
