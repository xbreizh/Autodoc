package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public Provider transferInsert(Object obj) throws Exception {
        ProviderDTO dto = (ProviderDTO) obj;
        checkDataInsert(dto);
        Provider provider = new Provider();
        provider.setFirstName(dto.getFirstName().toUpperCase());
        provider.setLastName(dto.getLastName().toUpperCase());
        provider.setPhoneNumber1(dto.getPhoneNumber1().toUpperCase());
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

    public Provider transferUpdate(Object obj) throws Exception {
        LOGGER.info("transferring update data");
        ProviderDTO dto = (ProviderDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new Exception("id cannot be null");
        Provider provider = (Provider) providerDao.getById(id);
        if (provider == null) throw new Exception("invalid id");
        if (dto.getFirstName() != null) {
            provider.setFirstName(dto.getFirstName().toUpperCase());
        }
        if (dto.getLastName() != null) {
            provider.setLastName(dto.getLastName().toUpperCase());
        }
        if (dto.getPhoneNumber1() != null) {
            provider.setPhoneNumber1(dto.getPhoneNumber1().toUpperCase());
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


    public void checkDataInsert(Object entity) throws Exception {
        List<Search> searchList = new ArrayList<>();
        ProviderDTO dto = (ProviderDTO) entity;
        Search search1 = new Search("firstName", "=", dto.getFirstName().toUpperCase());
        Search search2 = new Search("lastName", "=", dto.getLastName().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        List<Provider> providers = providerDao.getByCriteria(searchList);
        if (!providers.isEmpty())
            throw new Exception("that client already exist: " + dto.getFirstName() + " " + dto.getLastName());

    }
}
