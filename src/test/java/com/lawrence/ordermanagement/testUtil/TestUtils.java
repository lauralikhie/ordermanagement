package com.lawrence.ordermanagement.testUtil;

import com.lawrence.ordermanagement.entity.User;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;

public class TestUtils {

    public static User getSampleUser() {
        User user = new User();
        user.setName("Suru");
        user.setPassword("lawru");
        user.setEmail("test@test.com");
        return user;
    }

    public static UserRegistrationRequest getSampleUserRegisterRequest() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setEmail("test@test.com");
        request.setName("Suru");
        request.setPassword("lawru");
        return request;
    }
}
