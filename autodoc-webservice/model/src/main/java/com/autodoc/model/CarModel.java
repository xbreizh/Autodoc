package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carModel")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel")
    private List<Car> cars;

    @OneToMany(mappedBy = "carModel")
    private List<Piece> pieces;
}
