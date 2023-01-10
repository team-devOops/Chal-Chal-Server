package com.chalchal.chalchalsever.global.util;

/**
 * 랜덤 값 관련 util 정의한 class입니다.
 *
 * @author zinzoddari
 */
public class RandomUtils {

    private static final int RANDOM_MIN_NUM = 0;
    private static final int RANDOM_MAX_NUM = 9;

    /**
     * 단순 랜덤값 관련 메소드
     *
     * @param start 시작 값, end 최대 값
     * @return int 랜덤 값 출력
     */
    public static int getRandomInt(int start, int end) {
        return (int) (Math.random() * end) + start;
    }

    /**
     * 입력된 자릿 수 만큼 반환하는 랜덤값 관련 메소드
     *
     * @param length 자릿 수
     * @return int length 자릿 수 랜덤 값 출력
     */
    public static String getRandomIntByLength(int length) {
        String random = "";
        for (int i = 0; i < length; i++) {
            random += getRandomInt(RANDOM_MIN_NUM, RANDOM_MAX_NUM);
        }
        return random;
    }
}
