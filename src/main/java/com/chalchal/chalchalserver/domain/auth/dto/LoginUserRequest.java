package com.chalchal.chalchalserver.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    private String email;
    private String password;
}
