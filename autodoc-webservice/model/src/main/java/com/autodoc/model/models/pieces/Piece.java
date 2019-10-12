package com.autodoc.model.models.pieces;

import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.tasks.SubTask;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "piece")
@Getter
@Setter
@ToString
public class Piece /*implements Serializable*/ {

    // Constructors


    public Piece() {
    }

    public Piece(Provider provider, PieceType pieceType, String name, String brand, long buyingPrice, long sellPrice) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;


    @ManyToOne
    private SubTask subTask;

    @ManyToOne
    private CarModel carModel;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private PieceType pieceType;


    @NonNull
    private String name;

    @NonNull
    private String brand;

    @NonNull
    private long buyingPrice;


    private long sellPrice;
}
