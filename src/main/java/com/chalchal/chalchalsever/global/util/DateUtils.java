package com.chalchal.chalchalsever.global.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.time.format.DateTimeFormatter;

/**
 * 날짜 관련 util 정의한 class입니다.
 * 해당 Util의 기본 포맷은 [ yyyyMMddHHmmss ] 입니다.
 *
 * @author zinzoddari
 */
public class DateUtils {

    /** Date format */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN_FORMAT = "yyyyMMdd";
    /** Date Time format */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME = "yyyyMMddHHmmss";

    public static final String DATE_TIME_FORMAT = "HHmm";

    public static final String DATE_TIME_HMS_FORMAT = "HHmmss";

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
        return getDateByPattern(dt, pattern);
    }

    /**
     * 입력된 날짜에서 day만큼 날을 더한다.
     * @param date 입력된 특정 날짜(YYYYMMDD)
     * @param day 더하고자 하는 날짜
     * @return 특정 날짜에서 day만큼 더해진 날짜 값
     */
    public static String plusDayByDate(String date, int day) {
        return plusDayByDate(date, day, DATE_PATTERN_FORMAT, DATE_PATTERN_FORMAT);
    }

    /**
     * 입력된 날짜에서 day만큼 날을 더한다.
     * @param date 입력된 특정 날짜
     * @param day 더하고자 하는 날짜
     * @param inputPattern 입력 할 패턴 값
     * @param outputPattern 출력 할 패턴 값
     * @return 특정 날짜에서 day만큼 더해진 날짜 값
     */
    public static String plusDayByDate(String date, int day, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt.plusDays(day), outputPattern);
    }

    /**
     * 현재 날짜에서 day만큼 날을 더한다.
     * @param day 더하고자 하는 날짜
     * @return 현재 날짜에서 day만큼 더해진 날짜 값
     */
    public static String plusDayByCurrentDay(int day) {
        return plusDayByDate(getCurrentDay(DATE_PATTERN_FORMAT), day);
    }

    /**
     * 입력된 날짜에서 month만큼 월을 더한다.
     * @param month 더하고자 하는 개월 수
     * @return 특정 날의 월이 month만큼 더해진 날짜 값
     */
    public static String plusMonthByDate(String date, int month) {
        return plusMonthByDate(date, month, DATE_PATTERN_FORMAT, DATE_PATTERN_FORMAT);
    }

    /**
     * 입력된 날짜에서 month만큼 월을 더한다.
     * @param month 더하고자 하는 개월 수
     * @return 특정 날의 월이 month만큼 더해진 날짜 값
     */
    public static String plusMonthByDate(String date, int month, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt.plusMonths(month), outputPattern);
    }

    /**
     * 현재 날짜에서 month만큼 월을 더한다.
     * @param month 더하고자 하는 개월 수
     * @return 특정 날의 월이 month만큼 더해진 날짜 값
     */
    public static String plusMonthByCurrentDay(int month) {
        return plusMonthByDate(getCurrentDay(DATE_PATTERN_FORMAT), month);
    }

    /**
     * 입력된 날짜에서 year만큼 년을 더한다.
     * @param year 더하고자 하는 년 수
     * @param inputPattern 입력 할 패턴 값
     * @param outputPattern 출력 할 패턴 값
     * @return 특정 날의 월이 year만큼 더해진 날짜 값
     */
    public static String plusYearByDate(String date, int year, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt.plusYears(year), outputPattern);
    }

    /**
     * 입력된 날짜에서 year만큼 년을 더한다.
     * @param year 더하고자 하는 년 수
     * @return 특정 날의 월이 year만큼 더해진 날짜 값
     */
    public static String plusYearByDate(String date, int year) {
        return plusYearByDate(date, year, DATE_PATTERN_FORMAT, DATE_PATTERN_FORMAT);
    }

    /**
     * 현재 날짜에서 year만큼 년을 더한다.
     * @param year 더하고자 하는 개월 수
     * @return 특정 날의 년이 year만큼 더해진 날짜 값
     */
    public static String plusYearByCurrentDay(int year) {
        return plusYearByDate(getCurrentDay(DATE_PATTERN_FORMAT), year);
    }

    /**
     * 주어진 날짜 및 시간을 패턴에 맞게 가져온다.
     * @param date 날짜 및 시간 (yyyyMMddHHmmss)
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String getDateByPattern(String date) {
        DateTime dt = new DateTime(date);
        return getDateByPattern(dt, DATE_TIME);
    }

    /**
     * 주어진 날짜 및 시간을 패턴에 맞게 가져온다.
     * @param date DateTime
     * @param outputPattern 출력 할 패턴 값
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String getDateByPattern(DateTime date, String outputPattern) {
        return date.toString(outputPattern);
    }

    public static String getDateByPattern(String date, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt, outputPattern);
    }

    /**
     * 현재 날짜 기준으로 hour만큼 시간을 더한 결과 값을 반환
     * @param hour 더할 시간
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusHourByCurrentDay(int hour) {
        return plusHourByDate(getCurrentDay(DATE_TIME), hour);
    }

    /**
     * 입력된 날짜 기준으로 hour만큼 시간을 더한 결과 값을 반환
     * @param date 기준 날짜
     * @param hour 더할 시간
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusHourByDate(String date, int hour) {
        return plusHourByDate(date, hour, DATE_TIME, DATE_TIME);
    }

    /**
     * 입력된 날짜 기준으로 hour만큼 시간을 더한 결과를 pattern에 맞는 값 반환
     * @param date 기준 날짜
     * @param hour 더할 시간
     * @param inputPattern 입력 할 패턴 값
     * @param outputPattern 출력 할 패턴 값
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusHourByDate(String date, int hour, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt.plusHours(hour), outputPattern);
    }

    /**
     * 현재 날짜 기준으로 minute만큼 시간을 더한 결과 값을 반환
     * @param minute 더할 분
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusMinuteByCurrentDay(int minute) {
        return plusMinuteByDate(getCurrentDay(DATE_TIME), minute);
    }

    /**
     * 입력된 날짜 기준으로 minute만큼 시간을 더한 결과 값을 반환
     * @param date 기준 날짜
     * @param minute 더할 분
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusMinuteByDate(String date, int minute) {
        return plusMinuteByDate(date, minute, DATE_TIME, DATE_TIME);
    }

    /**
     * 입력된 날짜 기준으로 hour만큼 시간을 더한 결과를 pattern에 맞는 값 반환
     * @param date 기준 날짜
     * @param minute 더할 분
     * @param inputPattern 입력 할 패턴 값
     * @param outputPattern 출력 할 패턴 값
     * @return String pattern에 의한 날짜 및 시간
     */
    public static String plusMinuteByDate(String date, int minute, String inputPattern, String outputPattern) {
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(inputPattern));
        return getDateByPattern(dt.plusMinutes(minute), outputPattern);
    }
}
