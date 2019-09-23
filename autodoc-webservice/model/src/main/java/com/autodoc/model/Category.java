package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<PieceType> pieceTypes;

    private String name;
}
