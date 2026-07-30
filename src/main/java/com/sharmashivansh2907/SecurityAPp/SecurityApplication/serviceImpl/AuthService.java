package com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.LoginDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.LoginResponseDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        ); // here we are authenticating the user
        // if the user is authenticated then it will return the token
        User user =(User) authentication.getPrincipal();
        String accesstoken = jwtService.generateToken(user);
        String refreshtoken = jwtService.generateRefreshToken(user);
        return new LoginResponseDTO(user.getID(),accesstoken,refreshtoken);
    }

    public LoginResponseDTO refreshToken(String refreshToken){
        Long userID = jwtService.getUserIDFromToken(refreshToken);
        User user  =  userService.getUserByID(userID);
        String accessToken = jwtService.generateToken(user);
        return new LoginResponseDTO(user.getID(), accessToken, refreshToken);
    }


}
