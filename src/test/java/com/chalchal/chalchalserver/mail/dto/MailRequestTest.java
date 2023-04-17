package com.chalchal.chalchalserver.mail.dto;

import com.chalchal.chalchalserver.mail.domain.Mail;
import com.chalchal.chalchalserver.mail.domain.MailDiv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class MailRequestTest {

    @Test
    @DisplayName("mailRequest 빈생성자 생성")
    void mailRequestNoArgsConstructor() {
        MailRequest request = new MailRequest();

        assertThat(request).isNotNull();
    }

    @Test
    @DisplayName("mailRequest 생성")
    void mailRequest() {
        //given
        String svcNo = "svcNo";
        MailDiv mailDiv = MailDiv.SIGN_UP;
        String to = "to";
        String subject = "subject";
        String content = "content";

        //when
        MailRequest request = MailRequest.builder()
                .svcNo(svcNo)
                .mailDiv(mailDiv)
                .to(to)
                .subject(subject)
                .content(content)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(request.getSvcNo()).isEqualTo(svcNo);
            assertThat(request.getMailDiv()).isEqualTo(mailDiv);
            assertThat(request.getTo()).isEqualTo(to);
            assertThat(request.getSubject()).isEqualTo(subject);
            assertThat(request.getContent()).isEqualTo(content);
        });
    }

    @Test
    @DisplayName("svcNo null 값으로 mailRequest  생성")
    void mailRequestOfSvcNoIsNull() {
        //given
        String svcNo = null;
        MailDiv mailDiv = MailDiv.SIGN_UP;
        String to = "to";
        String subject = "subject";
        String content = "content";

        //when
        MailRequest request = MailRequest.builder()
                .svcNo(svcNo)
                .mailDiv(mailDiv)
                .to(to)
                .subject(subject)
                .content(content)
                .build();

        //then
        assertSoftly(softAssertions -> {
            assertThat(request.getSvcNo()).isNotNull();
            assertThat(request.getMailDiv()).isEqualTo(mailDiv);
            assertThat(request.getTo()).isEqualTo(to);
            assertThat(request.getSubject()).isEqualTo(subject);
            assertThat(request.getContent()).isEqualTo(content);
        });
    }

    @Test
    @DisplayName("mailRequest from")
    void mailRequestFrom() {
        //given
        String svcNo = "svcNo";
        MailDiv mailDiv = MailDiv.SIGN_UP;
        String to = "to";
        String subject = "subject";
        String content = "content";

        Mail mail = Mail.builder()
                .svcNo(svcNo)
                .mailDiv(mailDiv)
                .toMail(to)
                .subject(subject)
                .content(content)
                .build();

        //when
        MailRequest request = MailRequest.from(mail);

        //then
        assertSoftly(softAssertions -> {
            assertThat(request.getSvcNo()).isEqualTo(svcNo);
            assertThat(request.getMailDiv()).isEqualTo(mailDiv);
            assertThat(request.getTo()).isEqualTo(to);
            assertThat(request.getSubject()).isEqualTo(subject);
            assertThat(request.getContent()).isEqualTo(content);
        });
    }
}