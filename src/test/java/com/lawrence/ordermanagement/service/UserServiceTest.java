package com.lawrence.ordermanagement.service;

import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.repository.UserRepository;
import com.lawrence.ordermanagement.testUtil.TestUtils;
import com.lawrence.ordermanagement.util.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserUtils userUtils;
    @Mock
    UserRepository userRepository;


    @Test
    public void registerUserHappyPath() {
        UserRegistrationRequest request = TestUtils.getSampleUserRegisterRequest();

        when(userRepository.save(any())).thenReturn(TestUtils.getSampleUser());
        UserRegistrationResponse response = userService.registerUser(request).getBody();
        assertTrue(response.isSuccess());
    }

    @Test
    public void registerUserNegativeScenario() {
        UserRegistrationRequest request = TestUtils.getSampleUserRegisterRequest();
        when(userRepository.existsByEmail(any())).thenReturn(true);
        assertThrows(UserException.class, () -> userService.registerUser(request));
    }

}