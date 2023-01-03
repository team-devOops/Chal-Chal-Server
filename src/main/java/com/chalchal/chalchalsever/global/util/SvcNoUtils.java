package com.chalchal.chalchalsever.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SvcNoUtils {
    public static String getSvcNo() {
        return DateUtils.getCurrentDay("yyyyMMddHHmmssSSS") + (int)((Math.random()*10000));
    }
}
