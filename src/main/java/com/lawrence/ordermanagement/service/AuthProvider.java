package com.lawrence.ordermanagement.service;

import com.lawrence.ordermanagement.config.UserPrincipal;
import com.lawrence.ordermanagement.entity.User;
import com.lawrence.ordermanagement.exceptions.UserException;
import com.lawrence.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.lawrence.ordermanagement.UserConstants.INVALID_CREDENTIALS;

@Service
public class AuthProvider implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new UserPrincipal(user.getEmail(), user.getPassword());
        } else throw new UserException(INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }
}
