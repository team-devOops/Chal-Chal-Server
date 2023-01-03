package com.chalchal.chalchalsever.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthRequest {
    private String reqSvcNo;
    private Long id;
    private String email;
    private String code;
    private String limitDate;
    private String limitTime;
    private String authYn;
}
