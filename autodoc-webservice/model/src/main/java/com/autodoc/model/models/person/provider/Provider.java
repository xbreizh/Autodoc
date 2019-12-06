package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.Rate;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "provider")
@Setter
@Getter
public class Provider extends Person {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }


    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Address> addresses;


    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;
    private String website;
    private String email1;
    private String email2;
    @NotNull
    private String company;
    // @NonNull
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public Provider(String firstName, String lastName, String phoneNumber1, String email1, @NonNull String company) {
        super(firstName, lastName, phoneNumber1);
        this.email1 = email1;
        this.company = company;
    }

    public Provider() {
    }


    @Override
    public String toString() {
        return "Provider{" +
                "addresses=" + addresses +
                ", website='" + website + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", company='" + company + '\'' +
                ", rate=" + rate +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                "} " + super.toString();
    }
}
