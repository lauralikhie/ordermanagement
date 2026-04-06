package com.lawrence.ordermanagement.exceptions;

import com.lawrence.ordermanagement.model.BaseResponse;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleInvalidRequestParams(HttpServletResponse http) {
        http.setStatus(400);
        return UserRegistrationResponse.builder()
                .success(false)
                .message("Please enter valid email, name and password").build();
    }
}
