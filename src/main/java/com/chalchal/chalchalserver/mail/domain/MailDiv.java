package com.chalchal.chalchalserver.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailDiv {
    SIGN_UP("회원가입"),
    RESET_PASSWORD("비밀번호 재설정");

    private String contents;
}
