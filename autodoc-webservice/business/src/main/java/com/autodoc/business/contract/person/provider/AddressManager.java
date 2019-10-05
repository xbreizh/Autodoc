package com.autodoc.business.contract.person.provider;

import com.autodoc.model.models.person.provider.Address;

import java.util.List;

public interface AddressManager {


    String save(Address address);

    List<Address> getAll();
}
