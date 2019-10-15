package com.autodoc.dao.contract.person.client;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.person.client.Client;

public interface ClientDao extends IGenericDao {

    Client getByName(String name);
}
