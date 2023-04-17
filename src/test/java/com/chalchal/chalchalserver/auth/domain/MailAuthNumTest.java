package com.chalchal.chalchalserver.auth.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MailAuthNumTest {
    @Test
    @DisplayName("MailAuthNum 빈생성자 생성")
    void noArgsConstructor() {
        MailAuthNum mailAuthNum = MailAuthNum.creteMailAuthNum();

        assertThat(mailAuthNum.value().length()).isEqualTo(MailAuthNum.MAX_AUTH_NUM);
    }

    @Test
    @DisplayName("자릿수가 맞지 않는 authNum 생성")
    void authNumInvalid() {
        //given
        String authNum = "12345";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new MailAuthNum(authNum);
        });
    }

    @Test
    @DisplayName("MailAuthNum 같은지 비교")
    void equals() {
        MailAuthNum authNum1 = MailAuthNum.creteMailAuthNum();
        String param = authNum1.value();

        MailAuthNum authNum2 = new MailAuthNum(param);

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(authNum1.hashCode()).isEqualTo(authNum2.hashCode());
            assertThat(authNum1.equals(authNum2)).isTrue();
        });
    }
}