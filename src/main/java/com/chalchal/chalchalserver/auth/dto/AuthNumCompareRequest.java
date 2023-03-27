package com.chalchal.chalchalserver.auth.dto;

import lombok.Getter;

@Getter
public class AuthNumCompareRequest {
    private Long id;

    public AuthNumCompareRequest(Long id) {
        this.id = id;
    }
}
