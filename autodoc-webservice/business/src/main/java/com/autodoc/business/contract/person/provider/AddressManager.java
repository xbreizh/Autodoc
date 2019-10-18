package com.autodoc.business.contract.person.provider;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.provider.Address;

public interface AddressManager extends IGenericManager {


    String save(Address address);

    //   List<Address> getAll();
}
