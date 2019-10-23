package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ManufacturerDTO {


    // Constructors


    @NotNull(message = "name cannot be null")
    private String name;

    // Parameters


    public ManufacturerDTO(@NotNull(message = "name cannot be null") String name) {
        this.name = name;
    }
}
