package com.lawrence.ordermanagement.service;

import com.lawrence.ordermanagement.entity.User;
import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.model.UserLoginRequest;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.repository.UserRepository;
import com.lawrence.ordermanagement.util.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    UserRepository userRepository;
    UserUtils userUtils;


    public UserService(UserRepository userRepository, UserUtils userUtils, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userUtils = userUtils;
        this.encoder = encoder;
    }


    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            System.out.println("checking DB");
            throw new UserException("User Already Exists", HttpStatus.CONFLICT);
        } else {
            User user = new User();
            user.setName(userRegistrationRequest.getName());
            user.setEmail(userRegistrationRequest.getEmail());
            String password = userUtils.encryptPassword(userRegistrationRequest.getPassword());
            user.setPassword(password);
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("User registered");
            return response ;
        }

    }

    public String userLogin(UserLoginRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            if (validatePassword(request.getEmail(), request.getPassword())) {
                return "Login Successful";
            } else {
                throw new UserException("Incorrect password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new UserException("User not found, Please check email", HttpStatus.NOT_FOUND);
        }
    }

    public boolean validatePassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        String pwdFromDb = user.getPassword();
        if (encoder.matches(password, pwdFromDb)) {
            return true;
        }
        return false;
    }
}