package com.chalchal.chalchalserver.mail.domain;

import com.chalchal.chalchalserver.global.exception.BaseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MailDivTest {

    @Test
    public void mailDiv() {
        MailDiv signUp = MailDiv.SIGN_UP;

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(signUp.getContents()).isEqualTo("signup");
            assertThat(signUp.getDescription()).isEqualTo("회원가입");
        });
    }
    @Test
    public void of() {
        MailDiv signUp = MailDiv.of("signup");
        assertThat(signUp).isEqualTo(MailDiv.SIGN_UP);
    }

    @Test
    public void ofWithValidContents() {
        String param = "signup";

        MailDiv signUp = MailDiv.of(param);

        assertThat(signUp).isEqualTo(MailDiv.of(param));
    }

    @Test
    public void ofWithInvalidContents() {
        assertThrows(BaseException.class, () -> MailDiv.of("invalid"));
    }
}