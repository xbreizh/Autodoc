package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.dtos.person.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
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

    @Builder
    public EmployeeDTO(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, List<String> roles, String login, String password, Date startDate, Date lastConnection) {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), phoneNumber.toUpperCase());
        this.login = login.toUpperCase();
        this.password = password;
        this.lastConnection = lastConnection;
        this.startDate = startDate;
        this.roles = roles;

    }
}
