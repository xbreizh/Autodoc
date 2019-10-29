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



    public PieceType() {
    }


    public PieceType(String name) {
        this.name = name; }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany(mappedBy = "pieceType", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;


    @NonNull
    private String name;

}
