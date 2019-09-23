package com.autodoc.model;

import com.autodoc.model.person.Provider;

import javax.persistence.*;

@Entity
@Table(name = "piece")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private int number;

    private String name;

    private String Brand;


    private long buyingPrice;

    private long sellPrice;
}
