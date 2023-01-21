package com.chalchal.chalchalserver.utils;

import com.chalchal.chalchalserver.global.util.DateUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DateUtilsTest {
    @Test
    void 특정날짜에서_day_더하기() {
        //given
        String date = "20230112";

        //when
        String plusDay = DateUtils.plusDayByDate(date, 3);

        //then
        assertThat(plusDay).isEqualTo("20230115");
    }

    @Test
    void 특정포맷날짜에서_day_더하기() {
        //given
        String date = "2023-01-12";

        //when
        String plusDay = DateUtils.plusDayByDate(date, 3, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN);

        //then
        assertAll(
                () -> assertThat(plusDay).isEqualTo("2023-01-15"),
                () -> assertThat(DateUtils.getDateByPattern(plusDay, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN_FORMAT)).isEqualTo("20230115")
        );
    }

    @Test
    void 특정날짜에서_month_더하기() {
        //given
        String date = "20230112";

        //when
        String plus5Month = DateUtils.plusMonthByDate(date, 5);
        String plus13Month = DateUtils.plusMonthByDate(date, 13);

        //then
        assertAll(
                () -> assertThat(plus5Month).isEqualTo("20230612"),
                () -> assertThat(plus13Month).isEqualTo("20240212")
        );
    }

    @Test
    void 특정포맷날짜에서_month_더하기() {
        //given
        String date = "2023-01-12";

        //when
        String plus5Month = DateUtils.plusMonthByDate(date, 5, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN);
        String plus13Month = DateUtils.plusMonthByDate(date, 13, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN);

        //then
        assertAll(
                () -> assertThat(plus5Month).isEqualTo("2023-06-12"),
                () -> assertThat(plus13Month).isEqualTo("2024-02-12"),
                () -> assertThat(DateUtils.getDateByPattern(plus5Month, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN_FORMAT)).isEqualTo("20230612"),
                () -> assertThat(DateUtils.getDateByPattern(plus13Month, DateUtils.DATE_PATTERN, DateUtils.DATE_PATTERN_FORMAT)).isEqualTo("20240212")
        );
    }

    @Test
    void 특정날짜에서_year_더하기() {
        //given
        String date = "20230112";

        //when
        String plus5Year = DateUtils.plusYearByDate(date, 5);
        String plus13Year = DateUtils.plusYearByDate(date, 13);

        //then
        assertAll(
                () -> assertThat(plus5Year).isEqualTo("20280112"),
                () -> assertThat(plus13Year).isEqualTo("20360112")
        );
    }

    @Test
    void 특정날짜에서_hour_더하기() {
        //given
        String date = "20230112230000";

        //when
        String plus4Hour = DateUtils.plusHourByDate(date, 4);

        //then
        assertAll(
                () -> assertThat(plus4Hour).isEqualTo("20230113030000")
        );
    }

    @Test
    void 특정날짜에서_minute_더하기() {
        //given
        String date = "20230111232000";

        //when
        String plus50Min = DateUtils.plusMinuteByDate(date, 50);
        String plus2Min = DateUtils.plusMinuteByDate(date, 2);

        //then
        assertAll(
                () -> assertThat(plus50Min).isEqualTo("20230112001000"),
                () -> assertThat(plus2Min).isEqualTo("20230111232200")
        );
    }

    @Test
    void 테스트() {
        String date = DateUtils.plusMinuteByCurrentDay(10);
        System.out.println(date);
        System.out.println(DateUtils.getDateByPattern(date, DateUtils.DATE_TIME, DateUtils.DATE_TIME_HMS_FORMAT));
//        System.out.println(DateUtils.plusMinuteByDate(str, 1, DateUtils.DATE_TIME, DateUtils.DATE_TIME_HMS_FORMAT));
//
//        System.out.println(DateUtils.getCurrentTime("yyyyMMdd"));
//        System.out.println(UserJoinAuth.applyValidDate());
    }

//    @Test
//    void 현재날짜에서_day_더하기() {
//        assertThat(DateUtils.plusDayByCurrentDay(3)).isEqualTo("20230114");
//    }
//
//    @Test
//    void 현재날짜에서_month_더하기() {
//        assertThat(DateUtils.plusMonthByCurrentDay(3)).isEqualTo("20230411");
//    }
//
//    @Test
//    void 현재날짜에서_year_더하기() {
//        assertThat(DateUtils.plusYearByCurrentDay(3)).isEqualTo("20260111");
//    }
}
