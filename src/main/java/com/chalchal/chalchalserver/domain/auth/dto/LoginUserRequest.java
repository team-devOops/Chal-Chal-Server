package com.chalchal.chalchalserver.domain.auth.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
public class LoginUserRequest {
    @Email
    private String email;

    @NotEmpty
    @Min(0)

    private String password;
}
