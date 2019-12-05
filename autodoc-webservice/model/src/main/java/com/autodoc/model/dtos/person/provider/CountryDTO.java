package com.autodoc.model.dtos.person.provider;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CountryDTO {


    private int id;


    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 30, message = "name should have between {min} and {max} characters")
    private String name;

    public CountryDTO(@NotNull(message = "name cannot be null") String name) {
        this.name = name;
    }

    public CountryDTO() {
    }
}
