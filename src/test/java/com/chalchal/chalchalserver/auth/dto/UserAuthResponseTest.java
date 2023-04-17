package com.chalchal.chalchalserver.auth.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class UserAuthResponseTest {

    @Test
    @DisplayName("UserAuthResponse 빈생성자")
    void userAuthResponseNoArgsConstructor() {
        UserAuthResponse response = new UserAuthResponse();

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("UserAuthResponse 생성")
    void userAuthResponse() {
        //given
       String email ="email@test.com";
       String code = "code";
       String limitDate = "limitDate";
       String limitTime = "limitTime";
       Flag authYn = Flag.N;

       //when
        UserAuthResponse response = UserAuthResponse.builder()
                .email(email)
                .code(code)
                .limitDate(limitDate)
                .limitTime(limitTime)
                .authYn(authYn)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(response.getEmail()).isEqualTo(email);
            assertThat(response.getCode()).isEqualTo(code);
            assertThat(response.getLimitDate()).isEqualTo(limitDate);
            assertThat(response.getLimitTime()).isEqualTo(limitTime);
            assertThat(response.getAuthYn()).isEqualTo(authYn);
        });
    }
}