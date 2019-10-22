package com.autodoc.model.models.person.client;


import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client extends Person {

    public Client(String firstName, String lastName, String phoneNumber1) {
        super(firstName, lastName, phoneNumber1);
    }

    public Client() {
    }

    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private transient List<Car> cars;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private transient List<Bill> bills;


    @Override
    public String toString() {
        return "Client{" +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                '}';
    }
}
