package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.util.RandomUtils;

import java.util.Objects;

/**
 * 이메일 인증 번호
 * */
public class MailAuthNum {
    public static final int MAX_AUTH_NUM = 6;
    private final String authNum;

    public MailAuthNum(String authNum) {
        validateAuthNum(authNum);
        this.authNum = authNum;
    }

    public MailAuthNum(String authNum) {
        this.authNum = validateAuthNum(authNum);
    }

    public static MailAuthNum creteMailAuthNum() {
        return new MailAuthNum(RandomUtils.getRandomIntByLength(MAX_AUTH_NUM));
    }

    private static void validateAuthNum(String authNum) {
        if (authNum == null) {
            throw new IllegalArgumentException("[ERROR] + 이메일 인증번호는 null이면 안된다.");
        }

        if(MAX_AUTH_NUM != authNum.length()) {
            throw new IllegalArgumentException("[ERROR] " + MAX_AUTH_NUM + "자 여야 합니다.");
        }

    }

    public String value() {
        return authNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAuthNum that = (MailAuthNum) o;
        return Objects.equals(authNum, that.authNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authNum);
    }
}