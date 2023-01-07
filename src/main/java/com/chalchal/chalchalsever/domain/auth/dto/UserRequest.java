package com.chalchal.chalchalsever.domain.auth.dto;

import lombok.Getter;

@Getter
public class UserRequest {
    private String email;
    private String password;
    private String userId;
    private String nickName;
}
