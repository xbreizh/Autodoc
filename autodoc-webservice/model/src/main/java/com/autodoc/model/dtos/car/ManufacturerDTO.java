package com.autodoc.model.dtos.car;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ManufacturerDTO {


    // Constructors


    private int id;

    @NotNull(message = "name cannot be null")
    private String name;

    // Parameters


    public ManufacturerDTO(int id, @NotNull(message = "name cannot be null") String name) {
        this.id = id;
        this.name = name;
    }
}
