package com.autodoc.business.contract.person.provider;

import com.autodoc.model.models.person.provider.Provider;

import java.util.List;

public interface ProviderManager {


    String save(Provider provider);

    List<Provider> getAll();
}
