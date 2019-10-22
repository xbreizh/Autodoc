package com.autodoc.model.dtos.person.client;


import lombok.Data;

@Data
public class ClientDTO extends PersonDTO {

    public ClientDTO(String firstName, String lastName, String phoneNumber1) {
        super(firstName, lastName, phoneNumber1);
    }
}
