package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.provider.AddressDaoImpl;
import com.autodoc.model.models.person.provider.Address;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class AddressManagerImpl<T, D> extends AbstractGenericManager implements AddressManager {
    private AddressDaoImpl<Address> addressDao;
    private Logger logger = Logger.getLogger(AddressManagerImpl.class);


    public AddressManagerImpl(AddressDaoImpl<Address> addressDao) {
        super(addressDao);
        this.addressDao = addressDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }
}
