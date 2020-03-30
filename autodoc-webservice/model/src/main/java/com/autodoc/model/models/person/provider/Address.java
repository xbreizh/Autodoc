package com.autodoc.model.models.person.provider;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;


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

    public void setStreetName(String streetName) {
        this.streetName = streetName.toUpperCase();
    }

    public void setCity(String city) {
        this.city = city.toUpperCase();
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode.toUpperCase();
    }
}


