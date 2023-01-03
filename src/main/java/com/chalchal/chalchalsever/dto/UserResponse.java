package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.enumeration.UserRole;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;

    private String userId;
    private String nickName;
    private UserRole userRole;

    public UserResponse(String email, String userId, String nickName, UserRole userRole) {
        this.email = email;
        this.userId = userId;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getUserId(), user.getNickname(), user.getUserRole());
    }
}
