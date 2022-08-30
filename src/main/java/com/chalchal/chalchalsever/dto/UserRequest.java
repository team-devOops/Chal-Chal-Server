package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.enumeration.UserRole;
import lombok.Getter;

@Getter
public class UserRequest {
    private String email;
    private String password;
    private UserRole userRole;
}
