package com.autodoc.model.models.car;

import com.autodoc.model.models.pieces.PieceType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
@Setter @Getter  @ToString
public class Category implements Serializable {

    // Constructors


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<PieceType> pieceTypes;

    @NonNull
    private String name;
}
