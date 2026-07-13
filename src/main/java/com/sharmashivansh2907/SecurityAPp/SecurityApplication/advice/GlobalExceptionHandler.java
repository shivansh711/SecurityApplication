package com.sharmashivansh2907.SecurityAPp.SecurityApplication.advice;


import com.sharmashivansh2907.SecurityAPp.SecurityApplication.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResponseNotFound(ResourceNotFoundException resourceNotFoundException){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException authenticationException){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(authenticationException.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException jwtException){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(jwtException.getLocalizedMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errors =methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }



    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
