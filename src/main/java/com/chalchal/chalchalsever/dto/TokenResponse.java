package com.chalchal.chalchalsever.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponse {
    private long index;
    private long id;
    private long refresh_token_index;

    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
}
