package com.autodoc.model.models.car;


import com.autodoc.model.models.bill.Bill;
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
@Getter
@Setter
@ToString
public class Car implements Serializable {

    // Constructors

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @NonNull
    @Column(name = "registration")
    private String registration;

    // Parameters
    @NonNull
    @ManyToOne
    private CarModel carModel;

    @NonNull
    @ManyToOne
    private Client client;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Bill> bills;

    public Car() {
    }

    public Car(String registration, CarModel carModel, Client client) {
        this.registration = registration;
        this.carModel = carModel;
        this.client = client;
    }


}
