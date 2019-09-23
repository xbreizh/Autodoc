package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<CarModel> carModels;
}
