package com.autodoc.model.models.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carModel")
@Getter
@Setter
public class CarModel implements Serializable {

    // Constructors


    public CarModel() {
    }

    public CarModel(@NonNull Manufacturer manufacturer, @NonNull String name, @NonNull String description, @NonNull GearBox gearbox, @NonNull String engine, @NonNull FuelType fuelType) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NonNull
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private transient List<Car> cars;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE)
    private transient List<Piece> pieces;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;

    @NonNull
    private String engine;

    @NonNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gearbox=" + gearbox +
                ", engine='" + engine + '\'' +
                ", fuelType=" + fuelType +
                '}';
    }
}
