package com.autodoc.model.dtos.pieces;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class PieceDTO {

    @Min(value = 1, message = "buyingPrice cannot be null")
    private double buyingPrice;
    private int id;
    @Min(value = 1, message = "pieceTypeId cannot be null")
    private int pieceTypeId;

    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "brand cannot be null")
    private String brand;
    @Min(value = 1, message = "sellPrice cannot be null")
    private double sellPrice;
    private int quantity;


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
