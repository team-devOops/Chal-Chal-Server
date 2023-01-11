package com.chalchal.chalchalsever.domain.auth.entity;

import com.chalchal.chalchalsever.global.util.RandomUtils;
import lombok.Getter;

@Getter
public class MailAuthNum {
    public static final int MAX_AUTH_NUM = 6;
    private final String authNum;

    public MailAuthNum() {
        this.authNum = validateAuthNum(creteMailAuthNum());
    }

    private String creteMailAuthNum() {
        return RandomUtils.getRandomIntByLength(MAX_AUTH_NUM);
    }

    private String validateAuthNum(String authNum) {
        if(MAX_AUTH_NUM == authNum.length()) return authNum;
        throw new IllegalArgumentException("[ERROR] " + MAX_AUTH_NUM + "자 여야 합니다.");
    }
}