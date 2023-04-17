package com.chalchal.chalchalserver.auth.domain;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.*;

class UserMailAuthTest {
    @Test
    @DisplayName("UserMailAuth 빈생성자 생성")
    void noArgsConstructor() {
        UserMailAuth userMailAuth = new UserMailAuth();

        assertThat(userMailAuth).isNotNull();
    }

    @Test
    @DisplayName("UserMailAuth 생성")
    void userMailAuth() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String sendEmail = "sendEmail";
        String authCode = "123456";
        LocalDateTime validDate = LocalDateTime.now();
        Flag authYn = Flag.N;

        //when
        UserMailAuth userMailAuth = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(userMailAuth.getSvcNo()).isEqualTo(svcNo);
            assertThat(userMailAuth.getId()).isEqualTo(id);
            assertThat(userMailAuth.getSendEmail()).isEqualTo(sendEmail);
            assertThat(userMailAuth.getAuthCode()).isEqualTo(authCode);
            assertThat(userMailAuth.getValidDate().toLocalDate()).isEqualTo(validDate.toLocalDate());
            assertThat(userMailAuth.getAuthYn()).isEqualTo(authYn);
        });
    }

    @Test
    @DisplayName("UserMailAuth 인증 성공했을 때")
    void successAuth() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String sendEmail = "sendEmail";
        String authCode = "123456";
        LocalDateTime validDate = LocalDateTime.now();
        Flag authYn = Flag.N;

        UserMailAuth userMailAuth = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        //when
        userMailAuth.successAuth();

        //then
        assertSoftly(softAssertions -> {
            assertThat(userMailAuth.getAuthYn()).isEqualTo(Flag.Y);
            assertThat(userMailAuth.getSuccessAuthDate()).isNotNull();
        });
    }

    @Test
    @DisplayName("UserMailAuth validDate 설정")
    void getValidate() {
        //given
        LocalDateTime validDate = LocalDateTime.now().plusMinutes(UserMailAuth.AUTH_VALID_MAX_TIME);

        //when
        UserMailAuth userMailAuth = new UserMailAuth();

        //then
        assertThat(userMailAuth.getValidDate().toLocalDate()).isEqualTo(validDate.toLocalDate());
    }

    @Test
    @DisplayName("UserMailAuth authCode가 동일 했을 때")
    void isEqualAuthCode() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String sendEmail = "sendEmail";
        String authCode = "123456";
        LocalDateTime validDate = LocalDateTime.now();
        Flag authYn = Flag.N;

        UserMailAuth userMailAuth = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        UserMailAuth userMailAuth2 = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        //when
        boolean isEquals = userMailAuth.isEqualAuthCode(authCode);

        //then
        assertSoftly(softAssertions -> {
            assertThat(userMailAuth.hashCode()).isEqualTo(userMailAuth2.hashCode());
            assertThat(isEquals).isTrue();
        });
    }

    @Test
    @DisplayName("UserMailAuth authCode가 동일하지 않을 때")
    void isEqualAuthCodeInvalid() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String sendEmail = "sendEmail";
        String authCode = "123456";
        LocalDateTime validDate = LocalDateTime.now();
        Flag authYn = Flag.N;

        UserMailAuth userMailAuth = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        //when
        boolean isEquals = userMailAuth.isEqualAuthCode("654321");

        //then
        assertThat(isEquals).isFalse();
    }

    @Test
    @DisplayName("UserMailAuth toString")
    void UserMailAuthtoString() {
        //given
        String svcNo = "svcNo";
        Long id = 0L;
        String sendEmail = "sendEmail";
        String authCode = "123456";
        LocalDateTime validDate = LocalDateTime.now();
        Flag authYn = Flag.N;

        UserMailAuth userMailAuth = UserMailAuth.builder()
                .svcNo(svcNo)
                .id(id)
                .sendEmail(sendEmail)
                .authCode(authCode)
                .validDate(validDate)
                .authYn(authYn)
                .build();

        //when
        String toString = "UserJoinAuth{" +
                "reqSvcNo='" + userMailAuth.getSvcNo() + '\'' +
                ", id=" + userMailAuth.getId() +
                ", sendEmail='" + userMailAuth.getSendEmail() + '\'' +
                ", authCode='" + userMailAuth.getAuthCode() + '\'' +
                ", validDate='" + userMailAuth.getValidDate() + '\'' +
                ", authYn='" + userMailAuth.getAuthYn() + '\'' +
                '}';

        //then
        assertThat(userMailAuth.toString()).isEqualTo(toString);
    }
}