package com.chalchal.chalchalsever.domain;

public class MailAuthNum {
    public static final int MAX_AUTH_NUM = 6;
    private final String authNum;

    public MailAuthNum(String authNum) {
        this.authNum = validateAuthNum(authNum);
    }

    private String validateAuthNum(String authNum) {
        if(MAX_AUTH_NUM == authNum.length()) return authNum;
        throw new IllegalArgumentException("[ERROR] " + MAX_AUTH_NUM + "자 여야 합니다.");
    }
}
