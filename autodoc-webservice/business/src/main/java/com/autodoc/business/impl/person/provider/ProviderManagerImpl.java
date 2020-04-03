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
        ProviderDTO dto = mapper.map(entity, ProviderDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Provider dtoToEntity(Object entity) throws Exception {
        ProviderDTO dto = (ProviderDTO) entity;
        Provider provider = mapper.map(entity, Provider.class);
        checkIfDuplicate(dto);
        return provider;
    }

    public Provider transferInsert(Object obj) throws Exception {
        ProviderDTO dto = (ProviderDTO) obj;
        checkIfDuplicate(dto);
        Provider provider = new Provider();
        provider.setFirstName(dto.getFirstName().toUpperCase());
        provider.setLastName(dto.getLastName().toUpperCase());
        provider.setPhoneNumber(dto.getPhoneNumber().toUpperCase());
        if (dto.getEmail1() != null) {
            provider.setEmail1(dto.getEmail1());
        }
        if (dto.getEmail2() != null) {
            provider.setEmail2(dto.getEmail2());
        }
        if (dto.getCompany() != null) {
            provider.setCompany(dto.getCompany());
        }
       /* if (dto.getRate() != null) {
            provider.setRate(dto.getRate());
        }*/
        if (dto.getWebsite() != null) {
            provider.setWebsite(dto.getWebsite());
        }

        return provider;


    }

    public Provider transferUpdate(Object obj) throws InvalidDtoException {
        LOGGER.info("transferring update data");
        ProviderDTO dto = (ProviderDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("id cannot be null");
        Provider provider = (Provider) dao.getById(id);
        if (provider == null) throw new InvalidDtoException("invalid id");
        if (dto.getFirstName() != null) {
            provider.setFirstName(dto.getFirstName().toUpperCase());
        }
        if (dto.getLastName() != null) {
            provider.setLastName(dto.getLastName().toUpperCase());
        }
        if (dto.getPhoneNumber() != null) {
            provider.setPhoneNumber(dto.getPhoneNumber().toUpperCase());
        }
        if (dto.getEmail1() != null) {
            provider.setEmail1(dto.getEmail1());
        }
        if (dto.getEmail2() != null) {
            provider.setEmail2(dto.getEmail2());
        }
        if (dto.getCompany() != null) {
            provider.setCompany(dto.getCompany());
        }

        if (dto.getWebsite() != null) {
            provider.setWebsite(dto.getWebsite());
        }

        return provider;
    }


    public void checkIfDuplicate(Object entity) throws Exception {
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
