package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class CategoryDTO {

    // Constructors


    public CategoryDTO(@NonNull int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    // Parameters
    @NonNull
    private int id;

    @NonNull
    private String name;


}
