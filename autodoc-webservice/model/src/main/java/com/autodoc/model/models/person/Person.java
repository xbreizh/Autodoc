package com.autodoc.model.models.person;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public abstract class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @NotBlank
    protected String firstName;
    @NotBlank
    protected String lastName;
    //@NotBlank
    protected String phoneNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.toUpperCase();
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
