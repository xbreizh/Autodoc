package com.autodoc.model.models.pieces;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.tasks.SubTask;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "piece")
@Getter
@Setter
@ToString
public class Piece {

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



    public Piece() {
    }

    public Piece(Provider provider, PieceType pieceType, String name, String brand, long buyingPrice, long sellPrice) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubTask subTask;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CarModel carModel;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PieceType pieceType;


    @NonNull
    private String name;

    @NonNull
    private String brand;


    private long buyingPrice;


    private long sellPrice;
}
