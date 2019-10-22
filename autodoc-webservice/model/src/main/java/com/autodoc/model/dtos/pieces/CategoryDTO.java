package com.autodoc.model.dtos.pieces;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CategoryDTO {

    // Constructors


    // Parameters
    private int id;
    @NotNull(message = "name cannot be null")
    private String name;

    public CategoryDTO(int id, @NotNull(message = "name cannot be null") String name) {
        this.id = id;
        this.name = name;
    }


}
