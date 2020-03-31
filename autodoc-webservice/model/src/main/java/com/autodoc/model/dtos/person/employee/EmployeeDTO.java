package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmployeeDTO extends PersonDTO {


    @NotNull(message = "role should not be null")
    @Enumerated(EnumType.STRING)
    private List<String> roles;

    //@PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;


    @NotNull(message = "password cannot be null")
    private String password;

    //@PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastConnection;


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
