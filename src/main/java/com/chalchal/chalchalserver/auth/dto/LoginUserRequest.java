package com.chalchal.chalchalserver.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class LoginUserRequest {
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    @Builder
    public LoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
