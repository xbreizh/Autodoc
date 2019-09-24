package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Getter @Setter @ToString
public class Manufacturer implements Serializable {


    // Constructors

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<CarModel> carModels;
}
