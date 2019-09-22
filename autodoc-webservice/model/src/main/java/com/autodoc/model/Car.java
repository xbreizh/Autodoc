package com.autodoc.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "registration")
    private String registration;

    @Column(name = "model")
    @OneToOne
    private Model model;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "car")
    private List<Bill> bills;
}
