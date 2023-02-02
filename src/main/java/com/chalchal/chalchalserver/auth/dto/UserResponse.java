package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.auth.domain.ProfileImg;
import com.chalchal.chalchalserver.auth.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;

    private String userId;
    private String nickName;

    private String url;

    public UserResponse(String email, String userId, String nickName, String url) {
        this.email = email;
        this.userId = userId;
        this.nickName = nickName;
        this.url = url;
    }

    public static UserResponse from(User user, ProfileImg profileImg) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname(), profileImg.getUrl());
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname(), null);
    }
}
