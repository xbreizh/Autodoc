package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class PieceTypeForm {

    private int id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;


    public PieceTypeForm(@NotNull @Size(min = 2, max = 30) String name) {
        this.name = name;
    }

    public PieceTypeForm() {
    }


    @Override
    public String toString() {
        return "PieceTypeForm{" +
                "name='" + name + '\'' +
                '}';
    }
}