package com.autodoc.model.dtos.car;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ManufacturerDTO extends PersonDTO {

    private int identifier;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;

    public ManufacturerDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, int identifier, @NotNull(message = "name cannot be null") @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters") String name) {
        super(lastName, firstName, phoneNumber1);
        this.identifier = identifier;
        this.name = name;
    }

    public ManufacturerDTO() {
    }
}
