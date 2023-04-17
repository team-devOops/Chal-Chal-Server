package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.auth.domain.UserProfileImg;
import com.chalchal.chalchalserver.auth.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String email;

    private String userId;
    private String nickname;

    private String url;

    @Builder
    public UserResponse(String email, String userId, String nickname, String url) {
        this.email = email;
        this.userId = userId;
        this.nickname = nickname;
        this.url = url;
    }

    public static UserResponse from(User user, UserProfileImg userProfileImg) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname(), userProfileImg.getUrl());
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname(), null);
    }
}
