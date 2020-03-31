package com.autodoc.model.dtos.pieces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieceTypeDTO {


    private int id;


    @NotNull(message = "name cannot be null")
    private String name;


    @Override
    public String toString() {
        return "PieceTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
