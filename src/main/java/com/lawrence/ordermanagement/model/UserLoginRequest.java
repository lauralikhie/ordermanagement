package com.lawrence.ordermanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {

    @Email
    private String email ;
    @NotNull
    private String password;

    public void setEmail(String email) {
        this.email = email.trim();
    }
}
