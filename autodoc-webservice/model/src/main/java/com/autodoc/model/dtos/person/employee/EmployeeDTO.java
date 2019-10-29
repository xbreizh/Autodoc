package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.autodoc.model.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    @NotNull(message = "role should not be null")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @PastOrPresent
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;

    @PastOrPresent
    private Date lastConnection;

    public EmployeeDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, @NotNull(message = "role should not be null") List<Role> roles, @PastOrPresent Date startDate, @NotNull(message = "login cannot be null") String login) {
        super(lastName, firstName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
    }

    public EmployeeDTO() {
    }
}
