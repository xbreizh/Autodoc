package com.autodoc.business.contract.person.provider;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.provider.Provider;

public interface ProviderManager extends IGenericManager {


    String save(Provider provider);

    //  List<Provider> getAll();
}
