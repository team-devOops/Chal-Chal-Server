package com.chalchal.chalchalsever.domain.auth.dto;

import com.chalchal.chalchalsever.global.dto.Flag;
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
    private Flag authYn;
}
