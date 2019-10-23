package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ProviderManagerImpl<T, D> extends AbstractGenericManager implements ProviderManager {
    private ProviderDaoImpl<Provider> providerDao;
    private Logger logger = Logger.getLogger(ProviderManagerImpl.class);
    ModelMapper mapper ;
    public ProviderManagerImpl(ProviderDaoImpl<Provider> providerDao) {
        super(providerDao);
        this.mapper = new ModelMapper();
        this.providerDao = providerDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }
}
