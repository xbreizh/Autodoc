package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    private List<String> roles;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    //@PastOrPresent
    private Date startDate;

    @Size(min = 3, max = 12, message = "{login.size}")
    private String login;

    //@PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastConnection;

    @NotNull
    private String password;

    public EmployeeDTO(int id, String firstName, String lastName, String phoneNumber1, List<String> roles, Date startDate, String login, Date lastConnection) {
        super(firstName, lastName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
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
}
