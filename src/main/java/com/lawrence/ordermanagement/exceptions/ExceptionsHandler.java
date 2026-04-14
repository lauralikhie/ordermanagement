package com.lawrence.ordermanagement.exceptions;

import com.lawrence.ordermanagement.model.UserLoginResponse;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserRegistrationResponse> handleExistingUserException(UserException ex) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserRegistrationResponse> handleInvalidRequestParams(MethodArgumentNotValidException ex) {
        UserRegistrationResponse response = new UserRegistrationResponse();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        response.setMessage(errors);
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<UserLoginResponse> handleBadCredsForLogin(BadCredentialsException ex) {
        UserLoginResponse response = new UserLoginResponse();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
