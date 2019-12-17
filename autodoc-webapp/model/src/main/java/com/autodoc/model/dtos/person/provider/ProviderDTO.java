package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
