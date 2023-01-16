package com.chalchal.chalchalserver.domain.auth.dto;

import com.chalchal.chalchalserver.domain.auth.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;

    private String userId;
    private String nickName;

    public UserResponse(String email, String userId, String nickName) {
        this.email = email;
        this.userId = userId;
        this.nickName = nickName;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname());
    }
}
