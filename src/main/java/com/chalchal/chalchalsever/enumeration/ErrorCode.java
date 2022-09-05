package com.chalchal.chalchalsever.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EXPIRED_TOKEN("0000"),
    INVALID_TOKEN ("0001"),
    UNKNOWN_ERROR("9999");

    private String code;
}
