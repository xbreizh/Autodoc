package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    @NotNull(message = "role should not be null")
    @Enumerated(EnumType.STRING)
    private List<String> roles;

    @PastOrPresent
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;


    @NotNull(message = "password cannot be null")
    private String password;

    @PastOrPresent
    private Date lastConnection;

    public EmployeeDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber cannot be null") String phoneNumber, @NotNull(message = "role should not be null") List<String> roles, @PastOrPresent Date startDate, @NotNull(message = "login cannot be null") String login) {
        super(lastName, firstName, phoneNumber);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
    }

    public EmployeeDTO() {
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "roles=" + roles +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                "} " + super.toString();
    }

    public void setLogin(String login) {
        this.login = login.toUpperCase();
    }
}
