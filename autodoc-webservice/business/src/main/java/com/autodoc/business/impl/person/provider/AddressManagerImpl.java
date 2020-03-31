package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.model.dtos.person.provider.AddressDTO;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class AddressManagerImpl<T, D> extends AbstractGenericManager implements AddressManager {
    // @Inject
    CountryDao countryDao;
    private static final Logger LOGGER = Logger.getLogger(AddressManagerImpl.class);
    private ModelMapper mapper;
    //@Inject
    ProviderDao providerDao;
    private AddressDao addressDao;


    public AddressManagerImpl(AddressDao addressDao, CountryDao countryDao, ProviderDao providerDao) {
        //super(addressDao);
        this.addressDao = addressDao;
        this.mapper = new ModelMapper();
        this.countryDao = countryDao;
        this.providerDao = providerDao;
    }

    @Override
    public AddressDTO entityToDto(Object entity) {
        AddressDTO dto = mapper.map(entity, AddressDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Address dtoToEntity(Object entity) throws Exception {
        AddressDTO dto = (AddressDTO) entity;
        Address address = new Address();
        //checkDataInsert(dto);
        return address;
    }

    public Address transferInsert(Object obj) throws Exception {
        LOGGER.info("transferring data for insertion");
        AddressDTO dto = (AddressDTO) obj;
        LOGGER.info("dto received: " + dto);
        Address address = new Address();
        String countryName = dto.getCountryName();
        if (countryName == null) throw new Exception("country name shouldn t be empty");
        Country country = (Country) countryDao.getByName(countryName.toUpperCase());
        if (country == null) throw new Exception("country is invalid");
        address.setCountry(country);
        if (dto.getCity().isEmpty()) throw new Exception("city shouldn t be empty");
        address.setCity(dto.getCity().toUpperCase());
        if (dto.getStreetName().isEmpty()) throw new Exception("streetName shouldn t be empty");
        address.setStreetName(dto.getStreetName().toUpperCase());
        if (dto.getPostcode() != null) address.setPostcode(dto.getPostcode().toUpperCase());
        return address;
    }

    public Address transferUpdate(Object obj) throws Exception {
        LOGGER.info("transferring data for update");
        AddressDTO dto = (AddressDTO) obj;
        LOGGER.info("dto received: " + dto);
        String countryName = dto.getCountryName();
        int id = dto.getId();
        Address address = (Address) addressDao.getById(id);
        if (address == null) throw new Exception("invalid address id: " + id);
        if (countryName != null) {
            Country country = (Country) countryDao.getByName(dto.getCountryName().toUpperCase());
            if (country == null) throw new Exception("country is invalid");
            address.setCountry(country);
        }
        if (dto.getCity() != null) address.setCity(dto.getCity().toUpperCase());
        if (dto.getStreetName() != null) address.setStreetName(dto.getStreetName().toUpperCase());
        if (dto.getPostcode() != null) address.setPostcode(dto.getPostcode().toUpperCase());
        LOGGER.info("address to update: " + address);
        return address;

    }
}
