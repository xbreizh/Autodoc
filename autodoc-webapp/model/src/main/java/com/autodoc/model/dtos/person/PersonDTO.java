package com.autodoc.model.dtos.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@Getter
@ToString
@Setter
public abstract class PersonDTO implements Comparable {
    private int id;

    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;
    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;
    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber;

    public PersonDTO(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public PersonDTO() {
    }


    @Override
    public int compareTo(Object o) {
        PersonDTO dto = (PersonDTO) o;
        return Integer.compare(this.getId(), dto.getId());
    }


    public void setLastName(String lastName) {
        if (lastName != null)
            this.lastName = lastName.toUpperCase();
    }

    public void setFirstName(String firstName) {
        if (firstName != null)
            this.firstName = firstName.toUpperCase();
    }


}