package com.lawrence.ordermanagement.service;

import com.lawrence.ordermanagement.entity.User;
import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.model.UserLoginRequest;
import com.lawrence.ordermanagement.model.UserLoginResponse;
import com.lawrence.ordermanagement.model.UserRegistrationRequest;
import com.lawrence.ordermanagement.model.UserRegistrationResponse;
import com.lawrence.ordermanagement.repository.UserRepository;
import com.lawrence.ordermanagement.util.JwtUtil;
import com.lawrence.ordermanagement.util.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.lawrence.ordermanagement.UserConstants.*;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    UserRepository userRepository;
    UserUtils userUtils;
    JwtUtil jwtUtil;
    AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, UserUtils userUtils,
                       BCryptPasswordEncoder encoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userUtils = userUtils;
        this.encoder = encoder;
    }


    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new UserException(USER_ALEADY_EXISTS, HttpStatus.CONFLICT);
        } else {
            User user = new User();
            user.setName(userRegistrationRequest.getName());
            if (!userUtils.validate(userRegistrationRequest.getEmail())) {
                throw new UserException(INVALID_EMAIL_FORMAT, HttpStatus.BAD_REQUEST);
            }
            user.setEmail(userRegistrationRequest.getEmail());
            String password = userUtils.encryptPassword(userRegistrationRequest.getPassword());
            user.setPassword(password);
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("User registered");
            return response;
        }

    }

    public UserLoginResponse userLogin(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new UserException(INVALID_CREDENTIALS, HttpStatus.NOT_FOUND);
        }
        String pwdFromDb = user.getPassword();
        if (encoder.matches(request.getPassword(), pwdFromDb)) {
            response.setMessage(jwtUtil.generateToken(request.getEmail()));
            response.setSuccess(true);
            return response;
        } else {
            throw new UserException(INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }
    }

}