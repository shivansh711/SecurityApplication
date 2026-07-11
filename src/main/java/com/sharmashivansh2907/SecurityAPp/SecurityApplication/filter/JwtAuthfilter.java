package com.sharmashivansh2907.SecurityAPp.SecurityApplication.filter;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.JwtServiceImpl;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthfilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtServiceImpl;
    private final UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];


        Long userID =  jwtServiceImpl.getUserIDFromToken(token);
        if(userID !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userService.getUserByID(userID);
            UsernamePasswordAuthenticationToken  authenticationToken =
                    new UsernamePasswordAuthenticationToken(user,null,null);
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }


        filterChain.doFilter(request,response);

    }

}
