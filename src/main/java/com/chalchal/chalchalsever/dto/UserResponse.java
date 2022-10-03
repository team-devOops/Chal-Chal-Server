package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.enumeration.UserRole;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;
    private String name;
    private String phoneNo;
    private String nickName;
    private UserRole userRole;

    public UserResponse(String email, String name, String phoneNo, String nickName, UserRole userRole) {
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getName(), user.getPhoneNo(), user.getNickname(), user.getUserRole());
    }
}
