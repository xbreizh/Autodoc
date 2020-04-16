package com.autodoc.model.models.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PieceType {


    private int id;

    @NonNull
    private String name;

    public PieceType() {
    }


    public PieceType(String name) {
        this.name = name;
    }

}
