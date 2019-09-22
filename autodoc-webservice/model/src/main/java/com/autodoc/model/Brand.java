package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;
}
