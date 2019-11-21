package com.autodoc.business.contract;


import com.autodoc.model.Client;

public interface ClientManager extends GlobalManager{


    Client getById(String token, int id);


}
