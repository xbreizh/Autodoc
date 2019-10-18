package com.autodoc.model.models.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pieceType")
@Getter
@Setter
@ToString
public class PieceType {

    // Constructors

    public PieceType() {
    }


    public PieceType(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @OneToMany(mappedBy = "pieceType", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;

    @ManyToOne
    @NonNull
    private Category category;


    @NonNull
    private String name;

}
