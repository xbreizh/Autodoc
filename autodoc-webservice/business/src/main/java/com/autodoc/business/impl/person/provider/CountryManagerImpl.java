package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.CountryDaoImpl;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CountryManagerImpl<T, D> extends AbstractGenericManager implements CountryManager {
    private CountryDaoImpl<Country> providerDao;
    private static final Logger LOGGER = Logger.getLogger(CountryManagerImpl.class);
    ModelMapper mapper;

    public CountryManagerImpl(CountryDaoImpl<Country> providerDao) {
        super(providerDao);
        this.mapper = new ModelMapper();
        this.providerDao = providerDao;
    }


    @Override
    public CountryDTO entityToDto(Object entity) {
        CountryDTO dto = mapper.map(entity, CountryDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Country dtoToEntity(Object entity) throws Exception {
        CountryDTO dto = (CountryDTO) entity;
        Country country = mapper.map(entity, Country.class);
        checkDataInsert(dto);
        return country;
    }
}
