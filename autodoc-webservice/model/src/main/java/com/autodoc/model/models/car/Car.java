package com.autodoc.model.models.car;


import com.autodoc.model.models.Bill;
import com.autodoc.model.models.person.client.Client;
import lombok.Getter;
import lombok.NonNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @NonNull
    @Column(name = "registration")
    private String registration;


    @NonNull
    @ManyToOne
    private CarModel carModel;

    @NonNull
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "car")
    private List<Bill> bills;


}
