package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProviderDTO extends PersonDTO {

    private String website;


    @Email(message = "invalid email")
    @NotNull(message = "email cannot be null")
    private String email1;
    @Email(message = "invalid email")
    private String email2;

    @NotNull(message = "company cannot be null")
    private String company;

    private String rate;

    public ProviderDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1) {
        super(lastName, firstName, phoneNumber1);
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
                ", rate='" + rate + '\'' +
                "} " + super.toString();
    }

    public void setWebsite(String website) {
        if (website != null)
            this.website = website.toUpperCase();
    }

    public void setEmail1(String email1) {
        if (email1 != null)
            this.email1 = email1.toUpperCase();
    }

    public void setEmail2(String email2) {
        if (email2 != null)
            this.email2 = email2.toUpperCase();
    }

    public void setCompany(String company) {
        if (company != null)
            this.company = company.toUpperCase();
    }

    public void setRate(String rate) {
        if (rate != null)
            this.rate = rate.toUpperCase();
    }
}

