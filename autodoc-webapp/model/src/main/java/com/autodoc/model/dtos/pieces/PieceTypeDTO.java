package com.autodoc.model.dtos.pieces;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class PieceTypeDTO {



    private int id;


    @Min(value = 1, message = "categoryId cannot be null")
    private int categoryId;

    @NotNull(message = "name cannot be null")
    private String name;

    public PieceTypeDTO(@Min(value = 1, message = "categoryId cannot be null") int categoryId, @NotNull(message = "name cannot be null") String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public PieceTypeDTO() {
    }
}
