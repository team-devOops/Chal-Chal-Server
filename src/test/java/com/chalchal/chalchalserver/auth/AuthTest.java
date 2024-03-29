package com.chalchal.chalchalserver.auth;

import com.chalchal.chalchalserver.auth.domain.UserMailAuth;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    @Test
    void 인증번호_비교_실패() {
        String randomNum = "123456";
        assertThat(randomNum).isNotEqualTo("111111");
    }

    @Test
    void memTest(){
        UserMailAuth userMailAuth = UserMailAuth.builder().build();

        System.out.println(userMailAuth);
    }
}
