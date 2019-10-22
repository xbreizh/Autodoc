package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Rate;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ProviderDTO extends PersonDTO {

    private String website;


    // Parameters
    private int id;
    @Email
    private String email1;
    @Email
    private String email2;

    @NotNull(message = "company cannot be null")
    private String company;

    @NotNull(message = "rate cannot be null")
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public ProviderDTO(int id, @NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, String phoneNumber2, String website, int id1, @Email String email1, @Email String email2, @NotNull(message = "company cannot be null") String company, @NotNull(message = "rate cannot be null") Rate rate) {
        super(id, lastName, firstName, phoneNumber1, phoneNumber2);
        this.website = website;
        this.id = id1;
        this.email1 = email1;
        this.email2 = email2;
        this.company = company;
        this.rate = rate;
    }
}
