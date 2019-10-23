package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Rate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProviderDTO extends PersonDTO {

    private String website;


    // Parameters
    @Email(message = "invalid email")
    @NotNull(message = "email cannot be null")
    private String email1;
    @Email(message = "invalid email")
    private String email2;

    @NotNull(message = "company cannot be null")
    private String company;

    @NotNull(message = "rate cannot be null")
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public ProviderDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, @Email(message = "invalid email") @NotNull(message = "email cannot be null") String email1, @NotNull(message = "company cannot be null") String company, @NotNull(message = "rate cannot be null") Rate rate) {
        super(lastName, firstName, phoneNumber1);
        this.email1 = email1;
        this.company = company;
        this.rate = rate;
    }
}
