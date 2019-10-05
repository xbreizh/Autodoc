package com.autodoc.model.models.car;

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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;
    @NonNull
    private String name;

    // Parameters
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE)
    private List<CarModel> carModels;


    public Manufacturer() {
    }


    public Manufacturer(String name) {
        this.name = name;
    }
}
