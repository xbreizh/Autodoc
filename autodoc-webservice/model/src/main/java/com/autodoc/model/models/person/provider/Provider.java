package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "provider")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Generated
public class Provider extends Person {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }


    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Address> addresses;


    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;
    private String website;
    private String email1;
    private String email2;
    @NotNull
    private String company;


    public void setWebsite(String website) {
        this.website = website.toUpperCase();
    }

    public void setEmail1(String email1) {
        this.email1 = email1.toUpperCase();
    }

    public void setEmail2(String email2) {
        this.email2 = email2.toUpperCase();
    }

    public void setCompany(String company) {
        this.company = company.toUpperCase();
    }

    @Override
    public String toString() {
        return "Provider{" +
                "addresses=" + addresses +
                ", website='" + website + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", company='" + company + '\'' +
                /*  ", rate=" + rate +*/
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "} " + super.toString();
    }
}
