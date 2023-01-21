package com.chalchal.chalchalserver.auth.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
public class LoginUserRequest {
    @Email
    private String email;

    @NotEmpty
    @Min(8)
    @Max(32)
    private String password;
}
