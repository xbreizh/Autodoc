package com.autodoc.model.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@Generated
@AllArgsConstructor
public class Registration {

    @NotBlank(message = "registration cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "invalid registration (letters, numbers, between 8 and 10)")
    String reg;


}
