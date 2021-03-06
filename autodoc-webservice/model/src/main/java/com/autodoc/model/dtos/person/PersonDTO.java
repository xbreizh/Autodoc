package com.autodoc.model.dtos.person;

import com.autodoc.model.validation.ContactNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public abstract class PersonDTO {
    private int id;

    @Size(min = 3, max = 20, message = "the size for firstName should be between {min} and {max}")
    @NotBlank(message = "firstName cannot be null")
    private String firstName;
    @NotBlank(message = "lastName cannot be null")
    @Size(min = 3, max = 20, message = "the size for lastName should be between {min} and {max}")
    private String lastName;
    /* @Size(min = 8, max = 12, message = "the size for phoneNumber should be between {min} and {max}")
     @NotBlank(message = "phoneNumber cannot be null")*/
    @ContactNumberConstraint(message = "invalid phoneNumberYo")
    private String phoneNumber;


}