package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class CountryManagerImpl extends AbstractGenericManager implements CountryManager {
    private static final Logger LOGGER = Logger.getLogger(CountryManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private CountryDao dao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }

    @Override
    public CountryDTO entityToDto(Object entity) {
        if (entity == null) return null;
        CountryDTO dto = mapper.map(entity, CountryDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Country dtoToEntity(Object entity) throws Exception {
        if (entity == null) return null;
        CountryDTO dto = (CountryDTO) entity;
        Country country = mapper.map(entity, Country.class);
        checkIfDuplicate(dto);
        return country;
    }
}
