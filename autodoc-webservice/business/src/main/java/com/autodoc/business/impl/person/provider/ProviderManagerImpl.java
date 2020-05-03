package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.search.Search;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

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
        Provider provider = (Provider) dao.getById(dto.getId());
        if (dto.getFirstName() != null) provider.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) provider.setLastName(dto.getLastName());
        if (dto.getPhoneNumber() != null) provider.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getCompany() != null) provider.setCompany(dto.getCompany());
        if (dto.getAddress() != null) provider.setAddress(dto.getAddress());
        if (dto.getEmail1() != null) provider.setEmail1(dto.getEmail1());
        if (dto.getEmail2() != null) provider.setEmail2(dto.getEmail2());
        if (dto.getWebsite() != null) provider.setWebsite(dto.getWebsite());
        return provider;
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

    @Override
    @Transactional(propagation = REQUIRES_NEW)
    public boolean deleteById(int entityId) {
        LOGGER.info("deleting providerType");
        Provider provider = (Provider) dao.getById(entityId);
        if(provider!=null) {
            provider.setFirstName("deleted Provider");
            provider.setLastName("deleted Provider");
            dao.update(provider);
        }
        boolean deleteResult = dao.deleteById(entityId);
        LOGGER.info("delete result: "+deleteResult);
        if(!deleteResult){
            LOGGER.error("renaming item we can't delete");
            LOGGER.info(provider);
        }
        return true;
    }
}
