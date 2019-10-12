package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.Rate;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
@Setter
@Getter
@ToString
public class Provider extends Person {

    // Constructors


    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Address> addresses;

    // Parameters
    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;
    private String website;
    private String email1;
    private String email2;
    @NonNull
    private String company;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public Provider() {
    }


}
