package com.autodoc.model.dtos.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CountryDTO {

    // Constructor
    @NonNull
    private int id;

    // Parameters
    @NonNull
    private String name;

    public CountryDTO(@NonNull int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
