package com.autodoc.model.models.person.provider;

import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.enums.Rate;
import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.NonNull;
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

    @NonNull
    private String company;

    @NonNull
    private Rate rate;


}
