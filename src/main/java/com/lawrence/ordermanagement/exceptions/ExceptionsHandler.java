package com.lawrence.ordermanagement.exceptions;

import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UserException.class)
    public UserRegistrationResponse handleExistingUserException() {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(false);
        response.setMessage("User Already Exists");
        return response;
    }
}
