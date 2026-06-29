package com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {
    @NotBlank(message = "Email of the user can not be blank")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "User name can not be blank")
    @Size(min = 3 , max = 10, message = "Number of characters in name should be in the range:[3,10]")
    private String name;
}
