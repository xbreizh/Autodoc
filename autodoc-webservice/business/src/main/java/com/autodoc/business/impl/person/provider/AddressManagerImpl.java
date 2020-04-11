/*
package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.model.dtos.person.provider.AddressDTO;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.person.provider.Country;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class AddressManagerImpl extends AbstractGenericManager implements AddressManager {
    CountryDao countryDao;
    private static final Logger LOGGER = Logger.getLogger(AddressManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    ProviderDao providerDao;
    private AddressDao dao;


    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }

    @Override
    public AddressDTO entityToDto(Object entity) {
        return mapper.map(entity, AddressDTO.class);
    }

    @Override
    public Address dtoToEntity(Object entity) {
        return mapper.map(entity, Address.class);
    }

    public Address transferInsert(Object obj)  {
        LOGGER.info("transferring data for insertion");
        AddressDTO dto = (AddressDTO) obj;
        LOGGER.info("dto received: " + dto);
        Address address = new Address();
        String countryName = dto.getCountryName();
        if (countryName == null) throw new InvalidDtoException("country name shouldn t be empty");
        Country country = (Country) countryDao.getByName(countryName.toUpperCase());
        if (country == null) throw new InvalidDtoException("country is invalid");
        address.setCountry(country);
        if (dto.getCity().isEmpty()) throw new InvalidDtoException("city shouldn t be empty");
        address.setCity(dto.getCity().toUpperCase());
        if (dto.getStreetName().isEmpty()) throw new InvalidDtoException("streetName shouldn t be empty");
        address.setStreetName(dto.getStreetName().toUpperCase());
        if (dto.getPostcode() != null) address.setPostcode(dto.getPostcode().toUpperCase());
        return address;
    }

    public Address transferUpdate(Object obj)  {
        LOGGER.info("transferring data for update");
        AddressDTO dto = (AddressDTO) obj;
        LOGGER.info("dto received: " + dto);
        String countryName = dto.getCountryName();
        int id = dto.getId();
        Address address = (Address) dao.getById(id);
        if (address == null) throw new InvalidDtoException("invalid address id: " + id);
        if (countryName != null) {
            Country country = checkIfCountryExist(dto);
            address.setCountry(country);
        }
        if (dto.getCity() != null) address.setCity(dto.getCity().toUpperCase());
        if (dto.getStreetName() != null) address.setStreetName(dto.getStreetName().toUpperCase());
        if (dto.getPostcode() != null) address.setPostcode(dto.getPostcode().toUpperCase());
        LOGGER.info("address to update: " + address);
        return address;

    }

    private Country checkIfCountryExist(AddressDTO dto) {
        Country country = (Country) countryDao.getByName(dto.getCountryName().toUpperCase());
        if (country == null) throw new InvalidDtoException("country is invalid");
        return country;
    }
}
*/
