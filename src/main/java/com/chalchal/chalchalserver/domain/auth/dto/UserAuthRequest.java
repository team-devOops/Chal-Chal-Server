package com.chalchal.chalchalserver.domain.auth.dto;

import com.chalchal.chalchalserver.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthRequest {
    private String svcNo;
    private Long id;
    private String email;
    private String code;
    private Flag authYn;

    @Builder.Default
    private LocalDateTime validDate = UserJoinAuth.getValidity();
}
