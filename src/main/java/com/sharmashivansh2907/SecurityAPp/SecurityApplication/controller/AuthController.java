package com.sharmashivansh2907.SecurityAPp.SecurityApplication.controller;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.SignUpDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.UserDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.UserService;
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

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

}
