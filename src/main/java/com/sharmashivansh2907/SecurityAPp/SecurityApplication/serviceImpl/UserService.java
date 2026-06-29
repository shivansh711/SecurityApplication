package com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.SignUpDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.UserDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.Repo.UserRepo;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void isExistUserByEmail(String email){
        Optional<User> userExist = userRepo.findByEmail(email);
        if(userExist.isPresent()){
            throw new BadCredentialsException("User with email already exists " + email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User with email " + username + " not found "));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        isExistUserByEmail(signUpDTO.getEmail());

        User toCreateUser = modelMapper.map(signUpDTO,User.class);

        toCreateUser.setPassword(passwordEncoder.encode(toCreateUser.getPassword()));

        User toSaveUser = userRepo.save(toCreateUser);
        return modelMapper.map(toSaveUser,UserDTO.class);
    }
}
