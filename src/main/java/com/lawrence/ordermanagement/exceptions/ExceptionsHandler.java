package com.lawrence.ordermanagement.exceptions;

import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserRegistrationResponse> handleExistingUserException(UserException ex) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserRegistrationResponse> handleInvalidRequestParams(HttpServletRequest request ,  HttpServletResponse http) {
        System.out.println(request.toString());
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(false);
        response.setMessage("Please enter valid email, name and password");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
