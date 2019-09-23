package com.autodoc.model.person;


import com.autodoc.model.Bill;
import com.autodoc.model.Car;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client extends Person {

    @OneToMany(mappedBy = "client")
    private List<Car> cars;

    @OneToMany(mappedBy = "client")
    private List<Bill> bills;

}
