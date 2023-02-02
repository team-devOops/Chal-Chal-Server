package com.chalchal.chalchalserver.mail.domain;

import com.chalchal.chalchalserver.global.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MailDiv {
    SIGN_UP("signup", "회원가입"),
    RESET_PASSWORD("password", "비밀번호 재설정");

    private String contents;
    private String description;

    public static MailDiv of(String contents) {
        return Arrays.stream(MailDiv.values())
                .filter((div) -> div.contents.equals(contents))
                .findFirst()
                .orElseThrow(() -> BaseException.RESOURCE_NOT_FOUND_EXCEPTION);
    }
}
