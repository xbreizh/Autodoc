package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Rate;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@ToString
public class ProviderDTO extends PersonDTO {

    private String website;


    // Parameters
    @NonNull
    private int id;
    private String email1;
    private String email2;
    @NonNull
    private String company;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public ProviderDTO(@NonNull int id, @NonNull String lastName, @NonNull String firstName, @NonNull String phoneNumber1, String website, @NonNull int id1, String email1, String email2, @NonNull String company, @NonNull Rate rate) {
        super(id, lastName, firstName, phoneNumber1);
        this.website = website;
        this.id = id1;
        this.email1 = email1;
        this.email2 = email2;
        this.company = company;
        this.rate = rate;
    }
}
