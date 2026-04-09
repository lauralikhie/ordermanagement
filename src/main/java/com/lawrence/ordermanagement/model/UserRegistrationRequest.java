package com.lawrence.ordermanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequest {

    @NotNull
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    
    @NotNull
    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, message = "password needs to be altease 8 characters")
    private String password;

    @Email(message = "invalid email format")
    @NotNull
    private String email;
}
