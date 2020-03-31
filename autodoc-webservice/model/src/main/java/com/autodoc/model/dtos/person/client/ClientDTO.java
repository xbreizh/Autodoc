package com.autodoc.model.dtos.person.client;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@SuperBuilder
public class ClientDTO extends PersonDTO {


    @Override
    public String toString() {
        return "ClientDTO{} " + super.toString();
    }
}
