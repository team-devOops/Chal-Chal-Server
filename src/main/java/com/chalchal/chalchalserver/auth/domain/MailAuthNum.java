package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.util.RandomUtils;

import java.util.Objects;

public class MailAuthNum {
    public static final int MAX_AUTH_NUM = 6;
    private final String authNum;

    private MailAuthNum() {
        this.authNum = validateAuthNum(RandomUtils.getRandomIntByLength(MAX_AUTH_NUM));
    }

    public MailAuthNum(String authNum) {
        this.authNum = validateAuthNum(authNum);
    }

    public static MailAuthNum creteMailAuthNum() {
        return new MailAuthNum();
    }

    private static String validateAuthNum(String authNum) {
        if(MAX_AUTH_NUM == authNum.length()) return authNum;
        throw new IllegalArgumentException("[ERROR] " + MAX_AUTH_NUM + "자 여야 합니다.");
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