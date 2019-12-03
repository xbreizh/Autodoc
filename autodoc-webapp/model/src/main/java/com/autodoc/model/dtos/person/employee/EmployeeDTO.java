package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    private List<String> roles;

    @NotNull
    @PastOrPresent
    private Date startDate;

    @Size(min = 3, max = 12, message = "{login.size}")
    private String login;

    @PastOrPresent
    private Date lastConnection;

    public EmployeeDTO(int id, String firstName, String lastName, String phoneNumber1, List<String> roles, Date startDate, String login, Date lastConnection) {
        super(id, firstName, lastName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
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
}
