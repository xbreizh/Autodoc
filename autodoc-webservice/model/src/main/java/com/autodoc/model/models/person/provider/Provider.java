package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.pieces.Piece;
import lombok.*;

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
@Generated
public class Provider extends Person {

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();
    private String address = "";
    @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
    private List<Piece> pieces;


    /*  @OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
      private List<Address> addresses;*/
    private String website = "";
    private String email1 = "";
    private String email2 = "";
    @NotNull
    private String company = "";

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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
                "addresses=" + address +
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

    @Builder
    public Provider(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, String email1, String email2, String website, String company, String address) {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), phoneNumber.toUpperCase());
        if (email1 != null) this.email1 = email1.toUpperCase();
        if (email2 != null) this.email2 = email2.toUpperCase();
        if (company != null) this.company = company.toUpperCase();
        if (website != null) this.website = website.toUpperCase();
        if (address != null) this.address = address.toUpperCase();
    }


}
