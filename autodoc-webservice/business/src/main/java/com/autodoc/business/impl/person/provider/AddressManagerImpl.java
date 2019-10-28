package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.AddressDaoImpl;
import com.autodoc.model.dtos.person.provider.AddressDTO;
import com.autodoc.model.models.person.provider.Address;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class AddressManagerImpl<T, D> extends AbstractGenericManager implements AddressManager {
    private AddressDaoImpl<Address> addressDao;
    private static final Logger LOGGER = Logger.getLogger(AddressManagerImpl.class);
    private ModelMapper mapper;

    public AddressManagerImpl(AddressDaoImpl<Address> addressDao) {
        super(addressDao);
        this.mapper = new ModelMapper();
        this.addressDao = addressDao;
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
        Address address = mapper.map(entity, Address.class);
        checkDataInsert(dto);
        return address;
    }
}
