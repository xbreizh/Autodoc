package com.autodoc.model;


import com.autodoc.model.person.Client;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "car")
@Getter @Setter @ToString
public class Car implements Serializable {

    // Constructors

    public Car() {
    }

    public Car(String registration, CarModel carModel, Client client) {
        this.registration = registration;
        this.carModel = carModel;
        this.client = client;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
