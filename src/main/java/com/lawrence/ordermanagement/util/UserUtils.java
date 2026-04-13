package com.lawrence.ordermanagement.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserUtils {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    BCryptPasswordEncoder encoder;

    public UserUtils(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encryptPassword(@NotNull String password) {
        return encoder.encode(password);
    }

    public static boolean validate(String rawEmail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(rawEmail);
        return matcher.matches();
    }


}
