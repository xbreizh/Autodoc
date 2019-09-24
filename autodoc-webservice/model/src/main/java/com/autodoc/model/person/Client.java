package com.autodoc.model.person;


import com.autodoc.model.Bill;
import com.autodoc.model.Car;
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

    @OneToMany(mappedBy = "client")
    private List<Car> cars;

    @OneToMany(mappedBy = "client")
    private List<Bill> bills;





}
