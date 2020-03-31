package com.autodoc.business.impl;


import com.autodoc.business.contract.ProviderManager;
import com.autodoc.contract.GlobalService;
import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.dtos.person.provider.ProviderForm;
import com.autodoc.model.models.person.provider.Provider;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
@Builder
public class ProviderManagerImpl extends GlobalManagerImpl<Provider, ProviderDTO> implements ProviderManager {

    private static final Logger LOGGER = Logger.getLogger(ProviderManagerImpl.class);
    ProviderService service;

    GlobalService getService() {
        return service;
    }

    public Provider dtoToEntity(String token, Object obj) {

        ProviderDTO dto = (ProviderDTO) obj;
        LOGGER.info("dto: " + dto);
        Provider provider = new Provider();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        provider.setId(id);
        provider.setFirstName(dto.getFirstName());
        provider.setLastName(dto.getLastName());
        provider.setPhoneNumber(dto.getPhoneNumber());
        provider.setEmail(dto.getEmail1());
        provider.setWebsite(dto.getWebsite());
        provider.setCompany(dto.getCompany());
        LOGGER.info("provider transferred: " + provider);

        return provider;
    }

    public ProviderDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        ProviderForm dto = (ProviderForm) obj;
        LOGGER.info("dto: " + dto);
        ProviderDTO provider = new ProviderDTO();
        if (dto.getId() != 0) provider.setId(dto.getId());
        provider.setCompany(dto.getCompany());
        provider.setFirstName(dto.getFirstName());
        provider.setLastName(dto.getLastName());
        provider.setEmail1(dto.getEmail());
        provider.setPhoneNumber(dto.getPhoneNumber());
        LOGGER.info("provider transferred: " + provider);
        return provider;
    }

/*    List<Provider> convertList(String token, List<ProviderDTO> list) {
        LOGGER.info("converting list: "+list);
        List<Provider> newList = new ArrayList<>();
        for (ProviderDTO obj : list) {
            newList.add(dtoToEntity(token, obj));
        }
        LOGGER.info("new list: "+newList);
        return newList;
    }*/

}
