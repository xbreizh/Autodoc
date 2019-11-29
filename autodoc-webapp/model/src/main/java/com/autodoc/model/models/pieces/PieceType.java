package com.autodoc.model.models.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PieceType {


    private int id;
    private List<Piece> pieces;
    @NonNull
    private String name;

    public PieceType() {
    }


    public PieceType(String name) {
        this.name = name;
    }

}
