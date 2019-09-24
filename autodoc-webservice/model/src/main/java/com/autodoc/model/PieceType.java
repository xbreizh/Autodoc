package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pieceType")
@Getter @Setter @ToString
public class PieceType implements Serializable {

    // Constructors

    public PieceType() {
    }


    public PieceType(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<Piece> pieces;

    @ManyToOne
    private Category category;

    private String name;

}
