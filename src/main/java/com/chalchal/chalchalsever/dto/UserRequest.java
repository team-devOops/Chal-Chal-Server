package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.enumeration.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
    private UserRole userRole;;
}
