package com.autodoc.model.dtos.pieces;

import lombok.*;

import javax.validation.constraints.NotNull;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
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
