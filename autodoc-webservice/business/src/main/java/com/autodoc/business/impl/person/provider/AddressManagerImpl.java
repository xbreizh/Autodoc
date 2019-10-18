package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.impl.person.provider.AddressDaoImpl;
import com.autodoc.model.models.person.provider.Address;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public String save(Address address) {
        addressDao.create(address);
        return "car added";
    }

  /*  @Override
    public List<Address> getAll() {
        return null;
    }*/

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }
}
