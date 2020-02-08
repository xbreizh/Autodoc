package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    private String role;

    @NotNull
    @PastOrPresent
    private Date startDate;

    @Size(min = 3, max = 12, message = "{login.size}")
    private String login;

    @PastOrPresent
    private Date lastConnection;

    @NotNull
    private String password;

    public EmployeeDTO(int id, String firstName, String lastName, String phoneNumber1, String role, Date startDate, String login, Date lastConnection) {
        super(firstName, lastName, phoneNumber1);
        this.role = role;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
    }


    public EmployeeDTO() {
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "role=" + role +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                "} " + super.toString();
    }
}
