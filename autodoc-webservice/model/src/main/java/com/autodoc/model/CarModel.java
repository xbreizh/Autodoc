package com.autodoc.model;

import com.autodoc.model.enums.FuelType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carmodel")
@Getter @Setter @ToString
public class CarModel implements Serializable {

    // Constructors


    public CarModel(Manufacturer manufacturer, String name, String description, String gearbox, String engine, FuelType fuelType) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }

    public CarModel() {
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel")
    private List<Car> cars;

    @OneToMany(mappedBy = "carModel")
    private List<Piece> pieces;

    private String name;

    private String description;

    private String gearbox;

    private String engine;

    private FuelType fuelType;


}
