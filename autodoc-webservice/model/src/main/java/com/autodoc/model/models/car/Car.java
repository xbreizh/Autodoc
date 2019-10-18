package com.autodoc.model.models.car;


import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
public class Car /*implements Serializable */ {

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

    @NonNull
    @Column(name = "registration", unique = true)
    private String registration;

    @NonNull
    @ManyToOne
    private CarModel carModel;

    @NonNull
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private transient List<Bill> bills;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", carModel=" + carModel +
                ", client=" + client +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
