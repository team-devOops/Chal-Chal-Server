package com.chalchal.chalchalserver.mail.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class MailTest {

    @Test
    @DisplayName("Mail 빈생성자")
    void mailNoArgsConstructor() {
        Mail mail = new Mail();

        assertThat(mail).isNotNull();
    }

    @Test
    @DisplayName("Mail 생성")
    void mail() {
        //given
        String svcNo = "svcNo";
        MailDiv mailDiv = MailDiv.SIGN_UP;
        String fromMail = "a@naver.com";
        String toMail = "b@naver.com";
        String subject = "subject";
        String content = "content";

        //when
        Mail mail = Mail.builder()
                .svcNo(svcNo)
                .mailDiv(mailDiv)
                .fromMail(fromMail)
                .toMail(toMail)
                .subject(subject)
                .content(content)
                .build();

        assertSoftly(softAssertions -> {
            assertThat(mail.getSvcNo()).isEqualTo(svcNo);
            assertThat(mail.getMailDiv()).isEqualTo(mailDiv);
            assertThat(mail.getFromMail()).isEqualTo(fromMail);
            assertThat(mail.getToMail()).isEqualTo(toMail);
            assertThat(mail.getSubject()).isEqualTo(subject);
            assertThat(mail.getContent()).isEqualTo(content);
        });
    }
}