package com.autodoc.model.dtos.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Generated
public abstract class PersonDTO {
    private int id;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 3, max = 20, message = "the size for lastName should be between {min} and {max}")
    private String lastName;
    @Size(min = 3, max = 20, message = "the size for firstName should be between {min} and {max}")
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @Size(min = 8, max = 12, message = "the size for phoneNumber should be between {min} and {max}")
    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;


}