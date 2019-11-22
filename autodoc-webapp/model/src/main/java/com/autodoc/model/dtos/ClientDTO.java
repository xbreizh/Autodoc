package com.autodoc.model.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO extends PersonDTO{

    public ClientDTO(String lastName, String firstName, String phoneNumber1, String phoneNumber2) {
        super(lastName, firstName, phoneNumber1, phoneNumber2);
    }


    @Override
    public String toString() {
        return "ClientDTO{} " + super.toString();
    }
}
