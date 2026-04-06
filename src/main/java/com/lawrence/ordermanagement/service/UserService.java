package com.lawrence.ordermanagement.service;

import com.lawrence.ordermanagement.entity.User;
import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.repository.UserRepository;
import com.lawrence.ordermanagement.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserUtils userUtils;

    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
                throw new UserException("User already exists");
        } else {
            User user = new User();
            user.setName(userRegistrationRequest.getUsername());
            user.setEmail(userRegistrationRequest.getEmail());
            String password = userUtils.encryptPassword(userRegistrationRequest.getPassword());
            user.setPassword(password);
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("User registered");
        }
        return response;
    }
}