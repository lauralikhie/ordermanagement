package com.lawrence.ordermanagement.controller;

import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.model.UserLoginRequest;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> userRegistration(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        return new ResponseEntity<>(userService.registerUser(userRegistrationRequest), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(userService.userLogin(request), HttpStatus.OK);

    }

}
