package com.chalchal.chalchalserver.global.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 우리 프로젝트에서 UUID 역할을 하는 클래스 입니다.
 * 총 32자로 구성되어 있으며, 구조는 [ 현재 날짜 시간 + 프로젝트 구분 + 난수 ] 로 이루어져 있습니다.
 * 해당 클래스를 수정하게 될 경우 상의가 필요합니다.
 *
 * @author zinzoddari
 */
@Component
public class SvcNo {

    public static final int SVC_NO_MAX_SIZE = 32;

    private static String svcNoType;

    public SvcNo(@Value("${svcno.type}") String svcNoType) {
        this.svcNoType = svcNoType;
    }

    public static String getSvcNo() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String svcNo = localDateTime.format(DateTimeFormatter.ofPattern("YYYYmmddHHMMssSSS")) + svcNoType;

        return String.format(svcNo + "%0" + (SVC_NO_MAX_SIZE - svcNo.length()) + "d", (int)(Math.random() * (Integer.MAX_VALUE))).substring(0, SVC_NO_MAX_SIZE);
    }

    public static String getSvcNoType() {
        return svcNoType;
    }
}
