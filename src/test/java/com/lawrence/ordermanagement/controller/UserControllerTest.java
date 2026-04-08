package com.lawrence.ordermanagement.controller;

import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.repository.UserRepository;
import com.lawrence.ordermanagement.service.UserService;
import com.lawrence.ordermanagement.testUtil.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void validateUserRegistration() {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(true);
        response.setMessage("User registered");
        UserRegistrationRequest request = TestUtils.getSampleUserRegisterRequest();
        when(userService.registerUser(any())).thenReturn(ResponseEntity.ok(response));
        response = userController.userRegistration(request).getBody();
        assertEquals("User registered", response.getMessage());
        assertTrue(response.isSuccess());
    }
}