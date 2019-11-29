package com.autodoc.model.models.person.provider;

import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@ToString
public class Provider extends Person {


    private List<Address> addresses;


    private List<Piece> pieces;
    private String website;
    private String email1;
    private String email2;
    @NonNull
    private String company;
    @NonNull

    private String rate;

    public Provider(int id, String firstName, String lastName, String phoneNumber1, List<Address> addresses, List<Piece> pieces, String website, String email1, @NonNull String company, String rate) {
        super(id, firstName, lastName, phoneNumber1);
        this.addresses = addresses;
        this.pieces = pieces;
        this.website = website;
        this.email1 = email1;
        this.company = company;
        this.rate = rate;
    }

    public Provider() {
    }


}
