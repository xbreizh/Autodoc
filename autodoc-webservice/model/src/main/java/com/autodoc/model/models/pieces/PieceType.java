package com.autodoc.model.models.pieces;

import com.autodoc.model.enums.SearchType;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "pieceType")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class PieceType {


    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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

    public void setName(String name) {
        this.name = name.toUpperCase();
    }


    public static class PieceTypeBuilder {
        public PieceTypeBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }


    }
}
