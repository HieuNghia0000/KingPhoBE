package org.application.kingphobe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDTO {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
}
