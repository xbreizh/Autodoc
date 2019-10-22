package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Role;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeDTO extends PersonDTO {

    @NonNull
    private int id;

    @NotNull(message = "role should not be null")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @PastOrPresent
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;

    @PastOrPresent
    private Date lastConnection;


    public EmployeeDTO(@NonNull int id, @NonNull String lastName, @NonNull String firstName, @NonNull String phoneNumber1, @NonNull int id1, @NotNull(message = "role should not be null") List<Role> roles, @PastOrPresent Date startDate, @NotNull(message = "login cannot be null") String login, @PastOrPresent Date lastConnection) {
        super(id, lastName, firstName, phoneNumber1);
        this.id = id1;
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
    }
}
