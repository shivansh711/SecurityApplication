package com.sharmashivansh2907.SecurityAPp.SecurityApplication.controller;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.LoginDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.SignUpDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.UserDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.AuthService;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
