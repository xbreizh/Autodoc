package com.autodoc.model.dtos.car;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ManufacturerDTO extends PersonDTO {


    // Constructors


    @NotNull(message = "name cannot be null")
    private String name;

    // Parameters


    public ManufacturerDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, @NotNull(message = "name cannot be null") String name) {
        super(lastName, firstName, phoneNumber1);
        this.name = name;
    }
}
