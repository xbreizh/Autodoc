package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.CountryDaoImpl;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CountryManagerImpl<T, D> extends AbstractGenericManager implements CountryManager {
    private CountryDaoImpl<Country> providerDao;
    private Logger logger = Logger.getLogger(CountryManagerImpl.class);
    ModelMapper mapper;

    public CountryManagerImpl(CountryDaoImpl<Country> providerDao) {
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
