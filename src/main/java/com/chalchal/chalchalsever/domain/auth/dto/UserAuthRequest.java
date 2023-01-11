package com.chalchal.chalchalsever.domain.auth.dto;

import com.chalchal.chalchalsever.domain.auth.entity.MailAuthNum;
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
    private String validDate;
    private String validTime;
    private String authYn;
}
