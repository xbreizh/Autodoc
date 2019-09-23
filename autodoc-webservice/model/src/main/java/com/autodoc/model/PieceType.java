package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pieceType")
public class PieceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<Piece> pieces;

    private String name;

    @ManyToOne
    private Category category;

}
