package com.autodoc.model.models.pieces;

import com.autodoc.model.models.person.provider.Provider;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Piece {


    private int id;
    private Provider provider;
    private PieceType pieceType;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    private double buyingPrice;
    private double sellPrice;
    private int quantity;

    public Piece() {
    }


    public Piece(Provider provider, PieceType pieceType, String name, String brand, double buyingPrice, double sellPrice, int quantity) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }
}
