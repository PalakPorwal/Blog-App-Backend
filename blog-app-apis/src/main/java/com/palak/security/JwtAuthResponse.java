package com.palak.security;

import com.palak.payloads.UserDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponse {
    private String token;
    private UserDto user;
}
