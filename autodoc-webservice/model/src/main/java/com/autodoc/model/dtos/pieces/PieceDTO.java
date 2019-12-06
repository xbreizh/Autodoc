package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PieceDTO {

    public PieceDTO() {
    }

    @Min(value = 1, message = "buyingPrice cannot be null")
    private double buyingPrice;

    private int id;


    @Min(value = 1, message = "carModelId cannot be null")
    private int carModelId;

    @Min(value = 1, message = "providerId cannot be null")
    private int providerId;

    @Min(value = 1, message = "pieceTypeId cannot be null")
    private int pieceTypeId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "brand cannot be null")
    private String brand;
    @Min(value = 1, message = "sellPrice cannot be null")
    private double sellPrice;

    @Override
    public String toString() {
        return "PieceDTO{" +
                "buyingPrice=" + buyingPrice +
                ", id=" + id +
                ", carModelId=" + carModelId +
                ", providerId=" + providerId +
                ", pieceTypeId=" + pieceTypeId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
