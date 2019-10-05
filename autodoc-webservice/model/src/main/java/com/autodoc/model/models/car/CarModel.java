package com.autodoc.model.models.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carmodel")
@Getter
@Setter
@ToString
public class CarModel implements Serializable {

    // Constructors


    public CarModel() {
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NonNull
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE)
    private List<Car> cars;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String gearbox;

    @NonNull
    private String engine;

    @NonNull
    private FuelType fuelType;


}
