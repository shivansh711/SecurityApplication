package com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private Long ID;
    private String accessToken;
    private String refreshToken;

}
