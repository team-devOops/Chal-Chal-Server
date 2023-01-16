package com.chalchal.chalchalserver.domain.auth.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthMailRequest {
    private String email;
}
