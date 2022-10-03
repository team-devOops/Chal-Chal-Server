package com.chalchal.chalchalsever.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    private String MAIL_FROM;
    private final JavaMailSender mailSender;

    public void mailSend() {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(MAIL_FROM);
            helper.setTo(MAIL_FROM);
            helper.setTo(MAIL_FROM);
            helper.setSubject("xx");
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText("xx",true);
            mailSender.send(message);
        } catch ( MessagingException e) {
            e.printStackTrace();
        }
    }
}
