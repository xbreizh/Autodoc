package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {


    private List<String> roles;

    // @PastOrPresent
    private Date startDate;

    // @NotNull(message = "login cannot be null")
    private String login;

    //  @PastOrPresent
    private Date lastConnection;

    public EmployeeDTO(int id, String lastName, String firstName, String phoneNumber1, String phoneNumber2, List<String> roles, Date startDate, String login, Date lastConnection) {
        super(id, lastName, firstName, phoneNumber1, phoneNumber2);
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
