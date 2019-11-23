package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ManufacturerDTO {

    private int id;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;

    public ManufacturerDTO(int id, @NotNull(message = "name cannot be null") @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters") String name) {
        this.id = id;
        this.name = name;
    }

    public ManufacturerDTO() {
    }
}
