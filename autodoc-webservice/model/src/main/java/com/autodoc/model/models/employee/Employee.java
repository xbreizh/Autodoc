package com.autodoc.model.models.employee;

import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
        return "Employee{" +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", startDate=" + startDate +
                ", lastConnection=" + lastConnection +
                ", tokenExpiration=" + tokenExpiration +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void setLogin(String login) {
        this.login = login.toUpperCase();
    }


}
