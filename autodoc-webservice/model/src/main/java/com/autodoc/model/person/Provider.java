package com.autodoc.model.person;

import com.autodoc.model.Address;
import com.autodoc.model.Piece;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee")
public class Provider extends Person {

    private String company;

    @OneToMany(mappedBy = "provider")
    private List<Address> addresses;

    @OneToMany
    private List<Piece> pieces;


}
