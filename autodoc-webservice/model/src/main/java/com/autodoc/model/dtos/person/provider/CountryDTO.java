package com.autodoc.model.dtos.person.provider;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CountryDTO {

    // Constructor
    private int id;

    // Parameters
    @NotNull(message = "name cannot be null")
    private String name;

    public CountryDTO(@NotNull(message = "name cannot be null") String name) {
        this.name = name;
    }
}
