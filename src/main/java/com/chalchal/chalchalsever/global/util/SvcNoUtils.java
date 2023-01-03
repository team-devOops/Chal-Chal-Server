package com.chalchal.chalchalsever.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SvcNoUtils {

    private static final int SVC_NO_MAX_SIZE = 32;

    private static String svcNoType;

    @Value("${svcno.type}")
    private void setSvcNoType(String type) {
        this.svcNoType = type;
    }

    public static String getSvcNo() {
        String svcNo = DateUtils.getCurrentDay("yyyyMMddHHmmssSSS") + svcNoType;

        return String.format(svcNo + "%0" + (SVC_NO_MAX_SIZE - svcNo.length()) + "d", (int)(Math.random() * (Integer.MAX_VALUE))).substring(0, SVC_NO_MAX_SIZE);
    }
}
