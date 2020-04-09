package com.autodoc.model.models.employee;

import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Employee extends Person {


    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Bill> bills;
    @NotNull
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date startDate;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private String token;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastConnection;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date tokenExpiration;

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("ID", SearchType.INTEGER);
        result.put("firstname", SearchType.STRING);
        result.put("lastname", SearchType.STRING);
        result.put("login", SearchType.STRING);
        result.put("startDate", SearchType.DATE);
        return Collections.unmodifiableMap(result);
    }


    @Override
    public String toString() {
        int billSize = 0;
        if (bills != null) billSize = bills.size();
        int roleSize = 0;
        if (roles != null) roleSize = roles.size();
        return "Employee{" +
                "bills=" + billSize +
                ", roles=" + roleSize +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", lastConnection=" + lastConnection +
                ", tokenExpiration=" + tokenExpiration +
                "} " + super.toString();
    }

    public void setLogin(String login) {
        this.login = login.toUpperCase();
    }

    @Builder
    public Employee(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, List<Role> roles, String login, String password, Date startDate, Date lastConnection, Date tokenExpiration) {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), phoneNumber.toUpperCase());
        this.login = login.toUpperCase();
        this.password = password;
        this.lastConnection = lastConnection;
        this.startDate = startDate;
        this.tokenExpiration = tokenExpiration;
        this.roles = roles;

    }

}
