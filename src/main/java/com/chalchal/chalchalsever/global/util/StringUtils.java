package com.chalchal.chalchalsever.global.util;

import org.springframework.util.ObjectUtils;

/**
 * 자주 사용하는 String 관련 util 정의한 class입니다.
 *
 * @author zinzoddari
 */
public class StringUtils {
    /**
     * 값이 비어있지 않은지 확인하는 메소드
     *
     * @param str 비어있지 않은지 확인하고자 하는 param
     * @return 값이 있으면 true. 없으면 false
     */
    public static boolean isNotEmpty(String str) {
        if(str != null && !ObjectUtils.isEmpty(str)) return true;
        return false;
    }
}
