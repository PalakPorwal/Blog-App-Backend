package com.palak.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthRequest {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @Size(min = 3, max = 12, message = "Password must be min 3 & max 12 characters")
    private String password;
}
