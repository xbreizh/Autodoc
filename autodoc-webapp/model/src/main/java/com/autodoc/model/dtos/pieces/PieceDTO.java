package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class PieceDTO {

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
    @Min(value = 1, message = "buyingPrice cannot be null")
    private long buyingPrice;
    @Min(value = 1, message = "sellPrice cannot be null")
    private long sellPrice;

    public PieceDTO() {
    }

    public PieceDTO(@Min(value = 1, message = "carModelId cannot be null") int carModelId, @Min(value = 1, message = "providerId cannot be null") int providerId, @Min(value = 1, message = "pieceTypeId cannot be null") int pieceTypeId, @NotNull(message = "name cannot be null") String name, @NotNull(message = "brand cannot be null") String brand, @Min(value = 1, message = "buyingPrice cannot be null") long buyingPrice, @Min(value = 1, message = "sellPrice cannot be null") long sellPrice) {
        this.carModelId = carModelId;
        this.providerId = providerId;
        this.pieceTypeId = pieceTypeId;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }


}
