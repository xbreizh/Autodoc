package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Rate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class ProviderDTO extends PersonDTO {

    private String website="";


    @Email(message = "invalid email")
    @NotNull(message = "email cannot be null")
    private String email1="";
    @Email(message = "invalid email")
    private String email2="";

    @NotNull(message = "company cannot be null")
    private String company="";

    @Enumerated(EnumType.STRING)
    private Rate rate;

    private String address="";

    @Builder
    public ProviderDTO(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, String email1, String email2, String website, String company, String address) {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), phoneNumber.toUpperCase());
        if(email1!=null) this.email1 = email1.toUpperCase();
        if(email2!=null)this.email2 = email2.toUpperCase();
        if(company!=null)this.company = company.toUpperCase();
        if(website!=null)this.website = website.toUpperCase();
        if(address!=null)this.address = address.toUpperCase();
    }


}
