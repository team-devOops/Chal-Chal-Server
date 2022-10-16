package com.chalchal.chalchalsever.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthResponse {
    private String email;
    private String code;
    private String limitDate;
    private String limitTime;
    private String authYn;
}
