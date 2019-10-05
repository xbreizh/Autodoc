package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class ProviderManagerImpl implements ProviderManager {
    private ProviderDaoImpl<Provider> providerDao;
    private Logger logger = Logger.getLogger(ProviderManagerImpl.class);


    public ProviderManagerImpl(ProviderDaoImpl<Provider> providerDao) {
        this.providerDao = providerDao;

    }


    @Override
    public String save(Provider provider) {
        providerDao.create(provider);
        return "car added";
    }

    @Override
    public List<Provider> getAll() {
        return null;
    }
}
