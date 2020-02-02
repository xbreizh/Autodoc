package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PieceDTO {

    @Min(value = 1, message = "buyingPrice cannot be null")
    private double buyingPrice;
    private int id;
    @Min(value = 1, message = "pieceTypeId cannot be null")
    private int pieceTypeId;


   /* @Min(value = 1, message = "carModelId cannot be null")
    private int carModelId;*/
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "brand cannot be null")
    private String brand;
    @Min(value = 1, message = "sellPrice cannot be null")
    private double sellPrice;
    private int quantity;

    public PieceDTO() {
    }

    @Override
    public String toString() {
        return "PieceDTO{" +
                "buyingPrice=" + buyingPrice +
                ", id=" + id +
                //   ", carModelId=" + carModelId +
                ", pieceTypeId=" + pieceTypeId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
