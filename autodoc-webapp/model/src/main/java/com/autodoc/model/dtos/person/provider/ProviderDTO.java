package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProviderDTO extends PersonDTO {

    private String website;


    @Email(message = "invalid email")
    @NotNull(message = "email cannot be null")
    private String email1;
    @Email(message = "invalid email")
    private String email2;

    @NotNull(message = "company cannot be null")
    private String company;

    public ProviderDTO(int id, String firstName, String lastName, String phoneNumber1, String website) {
        super(id, firstName, lastName, phoneNumber1);
        this.website = website;
    }

    public ProviderDTO() {
    }


    @Override
    public String toString() {
        return "ProviderDTO{" +
                "website='" + website + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", company='" + company + '\'' +
                "} " + super.toString();
    }
}
