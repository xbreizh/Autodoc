package com.autodoc.business.contract;


import com.autodoc.model.Client;

public interface ClientManager {


    Client getById(String token, int id);


}
