package com.chalchal.chalchalserver.auth.domain;


import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MailAuthNumTest {

    @ParameterizedTest
    @DisplayName("메일 인증번호는 공백이나 Null이면 안된다")
    @NullAndEmptySource
    void test1(String mailNum) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new MailAuthNum(mailNum)
        );
    }

    @Test
    @DisplayName("메일 인증번호는 6자이어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "22", "333", "4444", "55556"})
    void test2() {
        String authNum = "1111111";
        assertThatIllegalArgumentException().isThrownBy(() ->
                new MailAuthNum(authNum)
        );
    }

    @Test
    @DisplayName("메일 인증번호가 생성된다")
    void test3() {
        final MailAuthNum mailAuthNum = MailAuthNum.creteMailAuthNum();

        assertThat(mailAuthNum).isNotNull();
    }

    @Test
    @DisplayName("메일 인증번호가 같다.")
    void test4() {
        final MailAuthNum mailAuthNum = MailAuthNum.creteMailAuthNum();
        final MailAuthNum makeAuthNum = new MailAuthNum(mailAuthNum.value());

        assertThat(mailAuthNum).isEqualTo(makeAuthNum);
    }

    @Test
    @DisplayName("메일 인증번호가 틀리다.")
    void test5() {
        final MailAuthNum source = new MailAuthNum("114444");
        final MailAuthNum target = new MailAuthNum("124444");

        assertThat(source).isNotEqualTo(target);
    }

    @Test
    @DisplayName("메일인증번호의 해시코드가 생성된다.")
    void test6() {
        final MailAuthNum source = new MailAuthNum("111111");

        assertThat(source.hashCode()).isNotNull();
    }


}