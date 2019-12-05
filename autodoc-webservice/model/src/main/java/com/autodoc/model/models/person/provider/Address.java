package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Setter
@Getter
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;


    public Address() {

    }

    @ManyToOne
    @NotNull(message = "country missing or invalid")
    private Country country;

    @ManyToOne
    @NonNull
    private Provider provider;
    @NotNull(message = "streetName missing or invalid")
    @Size(min = 2, max = 30, message = "streetName mush have between {min} and {max} characters")
    private String streetName;
    @NotNull(message = "city missing or invalid")
    @Size(min = 2, max = 30, message = "city mush have between {min} and {max} characters")
    private String city;


    private String postcode;

    public Address(@NotNull Country country, @NotNull String streetName, @NotNull String city) {
        this.country = country;
        this.streetName = streetName;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country=" + country +
                ", streetName='" + streetName + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}


