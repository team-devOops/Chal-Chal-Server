package com.chalchal.chalchalserver.auth.dto;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
public class UserRequest {

    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String nickName;
}
