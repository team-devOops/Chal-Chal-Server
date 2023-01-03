package com.chalchal.chalchalsever.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
