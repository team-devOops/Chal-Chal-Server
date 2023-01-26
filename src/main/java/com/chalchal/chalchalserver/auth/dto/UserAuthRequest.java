package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthRequest {
    private String svcNo;
    private Long id;
    @Email
    private String email;
    @NotEmpty
    private String code;
    private Flag authYn;

    @Builder.Default
    private LocalDateTime validDate = UserMailAuth.getValidity();
}
