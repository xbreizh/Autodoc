package com.autodoc.model.models.person.provider;

import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class Provider extends Person {


    /*  private List<Address> addresses;*/


    private List<Piece> pieces;
    private String website;
    private String email;
    @NonNull
    private String company;

    @NonNull


    public Provider(int id, String firstName, String lastName, String phoneNumber, /*List<Address> addresses,*/ List<Piece> pieces, String website, String email, @NonNull String company) {
        super(id, firstName, lastName, phoneNumber);
        /* this.addresses = addresses;*/
        this.pieces = pieces;
        this.website = website;
        this.email = email;
        this.company = company;
    }

    public Provider() {
    }


    @Override
    public String toString() {
        return "Provider{" +
                //  "addresses=" + addresses +
                ", pieces=" + pieces +
                ", website='" + website + '\'' +
                ", email1='" + email + '\'' +
                ", company='" + company + '\'' +
                "} " + super.toString();
    }
}
