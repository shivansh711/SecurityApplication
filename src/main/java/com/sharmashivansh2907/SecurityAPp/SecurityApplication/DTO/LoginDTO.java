package com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Email of the user can not be blank")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "User password can not be blank")
    @Min(value = 3,message = "Password length can not be less than 3")
    private String password;
}
