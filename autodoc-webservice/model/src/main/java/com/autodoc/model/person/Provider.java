package com.autodoc.model.person;

import com.autodoc.model.Address;
import com.autodoc.model.Piece;
import com.autodoc.model.enums.Rate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee")
@Setter @Getter @ToString
public class Provider extends Person {

    // Constructors

    public Provider(String firstName, String lastName, String phoneNumber1, String company) {
        super(firstName, lastName, phoneNumber1);
        this.company = company;
    }

    public Provider() {
    }

    // Parameters

    @OneToMany(mappedBy = "provider")
    private List<Address> addresses;

    @OneToMany
    private List<Piece> pieces;

    private String website;

    private String email1;

    private String email2;

    private String company;

    private Rate rate;


}
