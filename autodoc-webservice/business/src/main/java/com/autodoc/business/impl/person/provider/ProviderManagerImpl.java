package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ProviderManagerImpl<T, D> extends AbstractGenericManager implements ProviderManager {
    private ProviderDaoImpl<Provider> providerDao;
    private static final Logger LOGGER = Logger.getLogger(ProviderManagerImpl.class);
    ModelMapper mapper;

    public ProviderManagerImpl(ProviderDaoImpl<Provider> providerDao) {
        super(providerDao);
        this.mapper = new ModelMapper();
        this.providerDao = providerDao;
    }


    @Override
    public ProviderDTO entityToDto(Object entity) {
        ProviderDTO dto = mapper.map(entity, ProviderDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Provider dtoToEntity(Object entity) throws Exception {
        ProviderDTO dto = (ProviderDTO) entity;
        Provider provider = mapper.map(entity, Provider.class);
        checkDataInsert(dto);
        return provider;
    }
}
