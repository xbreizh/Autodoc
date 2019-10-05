package com.autodoc.model.models.person.client;


import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Setter @Getter @ToString
public class Client extends Person {

    public Client(String firstName, String lastName, String phoneNumber1) {
        super(firstName, lastName, phoneNumber1);
    }

    public Client() {
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Car> cars;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Bill> bills;





}
