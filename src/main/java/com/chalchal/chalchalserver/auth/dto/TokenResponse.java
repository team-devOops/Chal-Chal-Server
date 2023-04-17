package com.chalchal.chalchalserver.auth.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponse {
    private long index;
    private long id;
    private long refreshTokenIndex;

    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenResponse(long index, long id, long refreshTokenIndex, String accessToken, String refreshToken) {
        this.index = index;
        this.id = id;
        this.refreshTokenIndex = refreshTokenIndex;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
