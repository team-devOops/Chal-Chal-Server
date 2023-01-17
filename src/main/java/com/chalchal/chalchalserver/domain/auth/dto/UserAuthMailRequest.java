package com.chalchal.chalchalserver.domain.auth.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthMailRequest {
    @Email
    private String email;
}
