package com.sharmashivansh2907.SecurityAPp.SecurityApplication.service;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;

public interface JwtService {

    String generateToken(User user);

}
