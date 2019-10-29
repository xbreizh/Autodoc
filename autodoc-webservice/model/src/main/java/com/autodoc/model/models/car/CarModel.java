package com.autodoc.model.models.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "carModel")
@Getter
@Setter
public class CarModel {




    public CarModel() {
    }

    public CarModel(@NotNull Manufacturer manufacturer, @NotNull String name, @NotNull String description, @NotNull GearBox gearbox, @NotNull String engine, @NotNull FuelType fuelType) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NotNull
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private transient List<Car> cars;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private transient List<Piece> pieces;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;

    @NotNull
    private String engine;

    @NotNull
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
