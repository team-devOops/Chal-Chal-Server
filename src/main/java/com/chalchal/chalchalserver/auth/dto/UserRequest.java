package com.chalchal.chalchalserver.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String nickName;

    @Builder
    public UserRequest(String email, String password, String userId, String nickName) {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.nickName = nickName;
    }
}
