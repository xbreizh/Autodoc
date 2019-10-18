package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PieceTypeDTO {

    // Constructors

    @NonNull
    private int id;

    // Parameters
    @NonNull
    private int categoryId;
    @NonNull
    private String name;

    public PieceTypeDTO(@NonNull int id, @NonNull int categoryId, @NonNull String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
