package com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignUpDTO {

    @NotBlank(message = "Email of the user can not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "User password can not be blank")
    @Min(value = 3,message = "Password length can not be less than 3")
    private String password;

    @NotBlank(message = "User name can not be blank")
    @Size(min = 3 , max = 10, message = "Number of characters in name should be in the range:[3,10]")
    private String name;

}
