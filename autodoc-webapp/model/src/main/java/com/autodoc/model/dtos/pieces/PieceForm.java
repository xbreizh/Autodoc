package com.autodoc.model.dtos.pieces;

import com.autodoc.model.models.pieces.PieceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PieceForm {

    private int id;





    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "brand cannot be null")
    private String brand;

    @Min(value = 1, message = "buyingPrice cannot be null")
    private long buyingPrice;

    @Min(value = 1, message = "sellPrice cannot be null")
    private long sellPrice;

    @NotNull (message = "pieceTypeId cannot be null")
    private PieceType pieceType;

    @NotNull(message = "carModelId cannot be null")
    private int carModelId;


    public PieceForm(int id, @NotNull(message = "name cannot be null") String name, @NotNull(message = "brand cannot be null") String brand, @Min(value = 1, message = "buyingPrice cannot be null") long buyingPrice, @Min(value = 1, message = "sellPrice cannot be null") long sellPrice, @NotNull(message = "pieceTypeId cannot be null") PieceType pieceType, @NotNull(message = "carModelId cannot be null") int carModelId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
        this.pieceType = pieceType;
        this.carModelId = carModelId;
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
                ", pieceType=" + pieceType +
                ", carModelId=" + carModelId +
                '}';
    }
}