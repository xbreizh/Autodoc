package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class EmployeeDTO extends PersonDTO {

    @NonNull
    private int id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @NonNull
    private Date startDate;
    @NonNull
    private String login;
    @NonNull
    private String password;
    private String token;
    @NonNull
    private Date lastConnection;
    private Date tokenExpiration;


    public EmployeeDTO(@NonNull int id, @NonNull String lastName, @NonNull String firstName, @NonNull String phoneNumber1, @NonNull int id1, @NonNull List<Role> roles, @NonNull Date startDate, @NonNull String login, @NonNull String password, Date tokenExpiration) {
        super(id, lastName, firstName, phoneNumber1);
        this.id = id1;
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.password = password;
        this.tokenExpiration = tokenExpiration;
    }
}
