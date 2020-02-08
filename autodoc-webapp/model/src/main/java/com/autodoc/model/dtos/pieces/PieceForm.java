package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PieceForm {

    private int id;


    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "brand cannot be null")
    private String brand;

    @Min(value = 1, message = "buyingPrice cannot be null")
    private double buyingPrice;

    @Min(value = 1, message = "sellPrice cannot be null")
    private double sellPrice;

    @NotNull(message = "pieceTypeId cannot be null")
    private int pieceTypeId;
    private int quantity;

    public PieceForm(int id, @NotNull(message = "name cannot be null") String name, @NotNull(message = "brand cannot be null") String brand,
                     @Min(value = 1, message = "buyingPrice cannot be null") long buyingPrice,
                     @Min(value = 1, message = "sellPrice cannot be null") long sellPrice, @NotNull(message = "pieceTypeId cannot be null") int pieceTypeId, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
        this.pieceTypeId = pieceTypeId;
        this.quantity = quantity;
    }

    public PieceForm() {
    }


    @Override
    public String toString() {
        return "PieceForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellPrice=" + sellPrice +
                ", pieceTypeId=" + pieceTypeId +
                ", quantity=" + quantity +
                '}';
    }
}