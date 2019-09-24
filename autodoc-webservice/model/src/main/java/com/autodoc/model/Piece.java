package com.autodoc.model;

import com.autodoc.model.person.Provider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "piece")
@Getter @Setter @ToString
public class Piece implements Serializable {

    // Constructors


    public Piece() {
    }

    public Piece(Provider provider, PieceType pieceType, String name, String brand, long buyingPrice, long sellPrice) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        Brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String name;

    private String Brand;


    private long buyingPrice;

    private long sellPrice;
}
