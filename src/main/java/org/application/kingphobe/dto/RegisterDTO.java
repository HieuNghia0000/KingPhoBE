package org.application.kingphobe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.application.kingphobe.validation.annotation.ExistEmail;
import org.application.kingphobe.validation.annotation.ExistUsername;

@Data
public class RegisterDTO {

    @NotBlank(message = "Fullname must not be blank")
    private String fullname;

    @ExistUsername
    @NotBlank(message = "Username must not be blank")
    private String username;

    @ExistEmail
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Phone must not be blank")
    private String phone;


    @NotBlank(message = "Email must not be blank")
    private String password;
}
