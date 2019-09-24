package com.autodoc.model.models.pieces;

import com.autodoc.model.models.car.CarModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Getter
@Setter
@ToString
public class Manufacturer implements Serializable {


    // Constructors

    public Manufacturer() {
    }
    public Manufacturer(String name) {
        this.name = name;
    }

    // Parameters


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;


    @NonNull
    private String name;


    @OneToMany(mappedBy = "manufacturer")
    private List<CarModel> carModels;
}
