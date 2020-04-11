package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.search.Search;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
@Builder
public class ProviderManagerImpl extends AbstractGenericManager implements ProviderManager {
    private static final ModelMapper mapper = new ModelMapper();
    private static final Logger LOGGER = Logger.getLogger(ProviderManagerImpl.class);
    private ProviderDao dao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    @Override
    public ProviderDTO entityToDto(Object entity) {
        return mapper.map(entity, ProviderDTO.class);
    }

    @Override
    public Provider dtoToEntity(Object entity) {
        return mapper.map(entity, Provider.class);
    }

    public Provider transferInsert(Object obj) {
        ProviderDTO dto = (ProviderDTO) obj;
        checkIfDuplicate(dto);
        return dtoToEntity(dto);


    }

    public Provider transferUpdate(Object obj) {
        LOGGER.info("transferring update data");
        ProviderDTO dto = (ProviderDTO) obj;
        return dtoToEntity(dto);
    }


    public void checkIfDuplicate(Object entity) {
        List<Search> searchList = new ArrayList<>();
        ProviderDTO dto = (ProviderDTO) entity;
        Search search1 = new Search("firstName", "=", dto.getFirstName().toUpperCase());
        Search search2 = new Search("lastName", "=", dto.getLastName().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        List<Provider> providers = dao.getByCriteria(searchList);
        if (!providers.isEmpty())
            throw new InvalidDtoException("that client already exist: " + dto.getFirstName() + " " + dto.getLastName());

    }
}
