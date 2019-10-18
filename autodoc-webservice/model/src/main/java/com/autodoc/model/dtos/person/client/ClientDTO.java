package com.autodoc.model.dtos.person.client;


import com.autodoc.model.models.person.PersonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClientDTO extends PersonDTO {

    public ClientDTO(String firstName, String lastName, String phoneNumber1) {
        super(firstName, lastName, phoneNumber1);
    }

    public ClientDTO() {
    }


}
