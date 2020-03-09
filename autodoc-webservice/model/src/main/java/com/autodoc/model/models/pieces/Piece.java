package com.autodoc.model.models.pieces;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "piece")
@Getter
@Setter
public class Piece {

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
/*
    @ManyToOne
    private CarModel carModel;*/

    @ManyToMany(mappedBy = "pieces")
    private List<Bill> bills;

    @ManyToOne
    private Provider provider;
    @ManyToOne
    private PieceType pieceType;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    private double buyingPrice;
    private double sellPrice;
    private int quantity;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "piece_task",
            joinColumns = @JoinColumn(name = "pieces_id"),
            inverseJoinColumns = @JoinColumn(name = "tasks_id")
    )
    private List<Task> tasks;


    public Piece() {
    }

    public Piece(Provider provider, PieceType pieceType, String name, String brand, double buyingPrice, double sellPrice, int quantity) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toString() {
        int carModelId =0;
        int providerId=0;
        int pieceTypeId=0;
        //if(carModel!=null)carModelId=carModel.getId();
        if(pieceType!=null)pieceTypeId=pieceType.getId();
        if(provider!=null)providerId=provider.getId();
        return "Piece{" +
                "id=" + id +
                ", provider=" + providerId +
                ", pieceType=" + pieceTypeId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellPrice=" + sellPrice +
                ", quantity=" + quantity +
                '}';
    }
}
