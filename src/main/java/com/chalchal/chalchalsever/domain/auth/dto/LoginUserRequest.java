package com.chalchal.chalchalsever.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    private String email;
    private String password;
}
