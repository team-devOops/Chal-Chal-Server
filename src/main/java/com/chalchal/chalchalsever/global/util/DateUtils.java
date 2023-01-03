package com.chalchal.chalchalsever.global.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    /** Date format */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN_FORMAT = "yyyyMMdd";
    /** Date Time format */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 현재 날짜를 가져온다.
     * @return String 현재 날짜(yyyy-MM-dd)
     */
    public static String getCurrentDay() {
        return getCurrentTime(DATE_PATTERN);
    }

    /**
     * 주어진 날짜 패턴의 현재 날짜를 가져온다.
     * @param pattern
     * @return String pattern에 의한 현재 날짜
     */
    public static String getCurrentDay(String pattern) {
        return getCurrentTime(pattern);
    }

    /**
     * yyyy-MM-dd HH:mm 패턴의 현재 시간을 가져온다.
     * @return String 현재 시간(yyyy-MM-dd HH:mm)
     */
    public static String getCurrentTime() {
        return getCurrentTime(DATE_TIME_PATTERN);
    }

    /**
     * 주어진 패턴의 현재시간을 가져온다.
     * @param pattern
     * @return String pattern에 의한 현재 시간
     */
    public static String getCurrentTime(String pattern) {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.print(dt);
    }
}
