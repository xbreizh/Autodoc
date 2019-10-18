package com.autodoc.model.models.pieces;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Setter
@Getter
@ToString
public class Category {

    // Constructors


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;
    @OneToMany
    private List<PieceType> pieceTypes;

    // Parameters
    @NonNull
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
