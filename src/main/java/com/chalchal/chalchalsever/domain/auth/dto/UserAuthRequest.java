package com.chalchal.chalchalsever.domain.auth.dto;

import com.chalchal.chalchalsever.global.dto.Flag;
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
    private Flag authYn;
}
