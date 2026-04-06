package com.lawrence.ordermanagement.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String encryptPassword(@NotNull String password) {
        return encoder.encode(password);
    }

}
