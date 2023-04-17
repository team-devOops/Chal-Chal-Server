package com.chalchal.chalchalserver.auth.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthMailRequest {
    @Email
    @NotEmpty
    private String email;
}
