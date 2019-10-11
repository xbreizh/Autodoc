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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NonNull
    @Column(unique=true)
    private String name;

    // Parameters
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<CarModel> carModels;


    public Manufacturer() {
    }



    public Manufacturer(String name) {
        this.name = name;
    }
}
