package com.chalchal.chalchalsever.auth;

import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AuthTest {

    @Test
    void 인증번호_자릿수_오류() {
        String randomNum = "1234567";
        assertThatIllegalArgumentException().isThrownBy(() -> {
            //MailAuthNum mailAuthNum = new MailAuthNum(randomNum);
        });
    }

    @Test
    void 인증번호_비교_실패() {
        String randomNum = "123456";
        assertThat(randomNum).isNotEqualTo("111111");
    }

    @Test
    void memTest(){
        UserJoinAuth userJoinAuth = UserJoinAuth.builder().build();

        System.out.println(userJoinAuth);
    }
}
