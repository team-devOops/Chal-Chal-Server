package com.chalchal.chalchalsever.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UUIDUtils {
    public static String getUUID() {
        //TODO: UUID 다시 생각해보기
        return DateUtils.getCurrentDay("yyyyMMddHHmmssSSS") + (int)((Math.random()*10000));
    }
}
