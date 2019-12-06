package com.autodoc.model.models.pieces;

import com.autodoc.model.enums.SearchType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "pieceType")
@Getter
@Setter
public class PieceType {


    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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


    @Override
    public String toString() {
        return "PieceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
