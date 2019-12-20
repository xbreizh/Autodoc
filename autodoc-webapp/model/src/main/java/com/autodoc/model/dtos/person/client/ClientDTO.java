package com.autodoc.model.dtos.person.client;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO extends PersonDTO {


    public ClientDTO(int id, String lastName, String firstName, String phoneNumber1) {
        super(lastName, firstName, phoneNumber1);
    }


    public ClientDTO() {
    }

    @Override
    public String toString() {
        return "ClientDTO{} " + super.toString();
    }
}
