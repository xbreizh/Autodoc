package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PieceDTO {

    // Constructors


    @NonNull
    private int id;

    // Parameters
    @NonNull
    private int subTaskId;
    @NonNull
    private int carModelId;
    @NonNull
    private int providerId;
    @NonNull
    private int pieceTypeId;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    @NonNull
    private long buyingPrice;
    @NonNull
    private long sellPrice;

    public PieceDTO(@NonNull int id, @NonNull int subTaskId, @NonNull int carModelId, @NonNull int providerId, @NonNull int pieceTypeId, @NonNull String name, @NonNull String brand, @NonNull long buyingPrice, @NonNull long sellPrice) {
        this.id = id;
        this.subTaskId = subTaskId;
        this.carModelId = carModelId;
        this.providerId = providerId;
        this.pieceTypeId = pieceTypeId;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }
}
