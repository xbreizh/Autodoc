package com.autodoc.model;


import com.autodoc.model.person.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Setter @Getter
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "registration")
    private String registration;


    @ManyToOne
    private CarModel carModel;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "car")
    private List<Bill> bills;



}
