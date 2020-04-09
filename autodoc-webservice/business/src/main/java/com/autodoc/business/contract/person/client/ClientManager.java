package com.autodoc.business.contract.person.client;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.client.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientManager extends IGenericManager {


    Client checkIfIdIsValid(int id);
}
