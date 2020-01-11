package com.autodoc.model.models.person.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientList {


    private List<Client> list;

    public ClientList(List<Client> list) {
        this.list = list;
    }
}
