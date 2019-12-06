package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PieceTypeDTO {


    private int id;


    @NotNull(message = "name cannot be null")
    private String name;

    public PieceTypeDTO(@NotNull(message = "name cannot be null") String name) {
        this.name = name;
    }

    public PieceTypeDTO() {
    }

    @Override
    public String toString() {
        return "PieceTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
